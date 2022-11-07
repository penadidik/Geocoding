package project.penadidik.geocoding.model

import project.penadidik.geocoding.createUser
import project.penadidik.geocoding.createUserItem
import project.penadidik.geocoding.model.mapper.UserModelMapper
import org.junit.Before
import org.junit.Test

class UserBaseMapperTest {
    private lateinit var userItemMapper: UserModelMapper

    @Before
    fun setup() {
        userItemMapper = UserModelMapper()
    }

    @Test
    fun mapPresentationToDomainTest() {
        // generate user entity
        val item = createUserItem()
        // mapper
        val model = userItemMapper.mapToDomain(item)

        assert(item.id == model.id)
        assert(item.id == model.id)
        assert(item.login == model.login)
        assert(item.avatar == model.avatar)
        assert(item.htmlUrl == model.htmlUrl)
        assert(item.isFavorite == model.isFavorite)
    }

    @Test
    fun mapDomainToPresentationTest() {
        // generate model
        val model = createUser()

        // mapper to entity
        val item = userItemMapper.mapToPresentation(model)

        assert(item.id == model.id)
        assert(item.login == model.login)
        assert(item.avatar == model.avatar)
        assert(item.htmlUrl == model.htmlUrl)
        assert(item.isFavorite == model.isFavorite)
    }
}