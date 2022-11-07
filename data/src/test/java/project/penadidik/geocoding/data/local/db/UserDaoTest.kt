package project.penadidik.geocoding.data.local.db

import project.penadidik.geocoding.data.createUserEntity
import project.penadidik.geocoding.data.model.UserEntity
import io.reactivex.observers.TestObserver
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.concurrent.TimeUnit

@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(21))
class UserDaoTest : DbTest() {

    @Test
    fun testInsertAndGet() {
        // fake user entity
        val userEntity = createUserEntity()
        db.userDao().insert(userEntity)

        // create observer to get user entity with user id
        val testObserver = TestObserver<UserEntity>()
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS)

        // get user id
        db.userDao().findById(userEntity.id).toObservable().subscribe(testObserver)

        // data not null or no errors
        testObserver.assertNoErrors()
        testObserver.assertValue {
            userEntity == it
        }
    }

    @Test
    fun findNotExists() {
        val userId = anyInt()
        val testObserver = TestObserver<UserEntity>()
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS)

        db.userDao().findById(userId).toObservable().subscribe(testObserver)
        testObserver.assertValueCount(0)
    }

    @Test
    fun testInsertValueExists() {
        // create fake user
        val userEntity1 = UserEntity(id = 1,
            login = "Didik",
            avatar = "ini adalah penadidik",
            htmlUrl = "Penadidik",
            favorite = false)

        val userEntity2 = UserEntity(id = 2,
            login = "Didik2",
            avatar = "ini adalah penadidik",
            htmlUrl = "Penadidik",
            favorite = false)
        // first insert
        db.userDao().insert(userEntity1)

        // re-insert user
        db.userDao().insert(userEntity2)

        // check data
        val testObserver = TestObserver<UserEntity>()
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS)

        db.userDao().findById(2).toObservable().subscribe(testObserver)
        testObserver.assertNoErrors()
        testObserver.assertValue {
            it == userEntity2
        }
    }
}