package project.penadidik.geocoding.data.model

import org.junit.Before
import org.junit.Test
import project.penadidik.geocoding.data.createUser
import project.penadidik.geocoding.data.createUserEntity

class UserMapperTest {
    private lateinit var userEntityMapper: UserEntityMapper

    @Before
    fun setup() {
        userEntityMapper = UserEntityMapper()
    }

    @Test
    fun mapEntityToDomainTest() {
        // generate user entity
        val entity = createUserEntity()
        // mapper
        val model = userEntityMapper.mapToDomain(entity)

        assert(entity.id == model.id)
        assert(entity.login == model.login)
        assert(entity.avatar == model.avatar)
        assert(entity.htmlUrl == model.htmlUrl)
        assert(entity.favorite == model.isFavorite)
    }

    @Test
    fun mapDomainToEntityTest() {
        // generate model
        val model = createUser()

        // mapper to entity
        val entity = userEntityMapper.mapToEntity(model)

        assert(entity.id == model.id)
        assert(entity.login == model.login)
        assert(entity.avatar == model.avatar)
        assert(entity.htmlUrl == model.htmlUrl)
        assert(entity.favorite == model.isFavorite)
    }
}