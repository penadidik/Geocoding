package project.penadidik.cleanarchitecture.data.model

import project.penadidik.cleanarchitecture.data.createUser
import project.penadidik.cleanarchitecture.data.createUserEntity
import project.penadidik.cleanarchitecture.data.model.mapper.UserEntityMapper
import org.junit.Before
import org.junit.Test

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