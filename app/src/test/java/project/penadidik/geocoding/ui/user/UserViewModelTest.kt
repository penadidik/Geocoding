package project.penadidik.geocoding.ui.user

import androidx.lifecycle.Observer
import project.penadidik.geocoding.createUser
import project.penadidik.geocoding.domain.annotation.Redirect.Companion.OPEN_HOME_SCREEN
import project.penadidik.geocoding.domain.exception.*
import project.penadidik.geocoding.domain.model.Dialog
import project.penadidik.geocoding.domain.model.Redirect
import project.penadidik.geocoding.domain.model.Tag
import project.penadidik.geocoding.domain.model.User
import project.penadidik.geocoding.domain.usecase.user.SearchUserUseCase
import project.penadidik.geocoding.domain.usecase.user.SetFavoriteUserUseCase
import project.penadidik.geocoding.mock
import project.penadidik.geocoding.model.UserModel
import project.penadidik.geocoding.model.mapper.UserModelMapper
import project.penadidik.geocoding.ui.BaseViewModelTest
import io.reactivex.Single
import org.hamcrest.Matchers.nullValue
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test
import org.mockito.BDDMockito.`when`
import org.mockito.Mock

/**
 * Unit Test for [UserViewModel]
 */
class UserViewModelTest : BaseViewModelTest() {

    @Mock
    private lateinit var searchItemUseCase: SearchUserUseCase
    private lateinit var setFavoriteUserUseCase: SetFavoriteUserUseCase

    private lateinit var userViewModel: UserViewModel
    private val repoItemMapper = UserModelMapper()

    override fun setup() {
        super.setup()
        userViewModel = UserViewModel(searchItemUseCase, setFavoriteUserUseCase, repoItemMapper)
    }

    @Test
    fun searchNull() {
        userViewModel.query.value = null
        userViewModel.searchUser()

        assertThat(userViewModel.data.value, nullValue())
        assertEquals(userViewModel.loading.value, false)
    }

    @Test
    fun testSearchUserId() {
        val query = "Didik"
        userViewModel.query.value = query

        val item = createUser()
        val listItem: List<User> = arrayListOf(item)
        `when`(searchItemUseCase.createObservable(SearchUserUseCase.Params(query = query, pageNumber = 1, fromServer = true, sort = "ASC")))
            .thenReturn(Single.just(listItem))

        val observer = mock<Observer<List<UserModel>>>()
        userViewModel.data.observeForever(observer)

        userViewModel.searchUser()
        assertEquals(
            userViewModel.data.value,
            listItem.map { repoItemMapper.mapToPresentation(item) })
    }

    @Test
    fun testToastException() {
        val query = "query"
        userViewModel.query.value = query
        val message = "message"

        val toastException = ToastException(code = 1, message = message)
        `when`(searchItemUseCase.createObservable(SearchUserUseCase.Params(query = query, pageNumber = 1, fromServer = true, sort = "ASC")))
            .thenReturn(Single.error(toastException))

        userViewModel.searchUser()
        assertEquals(userViewModel.toastMessage.value, message)
    }

    @Test
    fun testSnackException() {
        val query = "query"
        userViewModel.query.value = query
        val message = "message"

        val snackException = SnackBarException(code = 1, message = message)
        `when`(searchItemUseCase.createObservable(SearchUserUseCase.Params(query = query, pageNumber = 1, fromServer = true, sort = "ASC")))
            .thenReturn(Single.error(snackException))

        userViewModel.searchUser()
        assertEquals(userViewModel.snackBarMessage.value, message)
    }

    @Test
    fun testInlineException() {
        val query = "query"
        userViewModel.query.value = query
        val tag = Tag(name = "", message = "")

        val inlineException = InlineException(code = 1, tags = *arrayOf(tag))
        `when`(searchItemUseCase.createObservable(SearchUserUseCase.Params(query = query, pageNumber = 1, fromServer = true, sort = "ASC")))
            .thenReturn(Single.error(inlineException))

        userViewModel.searchUser()
        assertEquals(userViewModel.inlineException.value?.first(), tag)
    }

    @Test
    fun testAlertException() {
        val query = "query"
        userViewModel.query.value = query
        val message = "message"
        val alertException = AlertException(code = 1, message = message)

        `when`(searchItemUseCase.createObservable(SearchUserUseCase.Params(query = query, pageNumber = 1, fromServer = true, sort = "ASC")))
            .thenReturn(Single.error(alertException))

        userViewModel.searchUser()
        assertEquals(userViewModel.alertException.value?.second, message)
    }

    @Test
    fun testDialogException() {
        val query = "query"
        userViewModel.query.value = query
        val message = "message"
        val dialogException = DialogException(code = 1, dialog = Dialog(message = message))

        `when`(searchItemUseCase.createObservable((SearchUserUseCase.Params(query = query, pageNumber = 1, fromServer = true, sort = "ASC"))))
            .thenReturn(Single.error(dialogException))

        userViewModel.searchUser()
        assertEquals(userViewModel.dialogException.value?.message, message)
    }

    @Test
    fun testRedirectException() {
        val query = "query"
        userViewModel.query.value = query
        val redirectException = RedirectException(code = 1, redirect = Redirect(redirect = OPEN_HOME_SCREEN))

        `when`(searchItemUseCase.createObservable((SearchUserUseCase.Params(query = query, pageNumber = 1, fromServer = true, sort = "ASC"))))
            .thenReturn(Single.error(redirectException))

        userViewModel.searchUser()
        assertEquals(userViewModel.redirectException.value?.redirect, OPEN_HOME_SCREEN)
    }

    @Test
    override fun clear() {
        super.clear()
        userViewModel.clear()
    }
}