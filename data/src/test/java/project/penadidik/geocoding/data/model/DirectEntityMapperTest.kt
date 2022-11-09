package project.penadidik.geocoding.data.model

import org.junit.Before
import org.junit.Test
import project.penadidik.geocoding.data.DirectTestModel
import project.penadidik.geocoding.data.local.model.mapper.DirectEntityMapper

class DirectEntityMapperTest {

    private lateinit var directEntityMapper: DirectEntityMapper

    @Before
    fun setup() {
        directEntityMapper = DirectEntityMapper()
    }

    @Test
    fun mapEntityToDomainTest() {
        val entity = DirectTestModel.createDirectEntity()
        val model = directEntityMapper.mapToDomain(entity)

        assert(entity.name == model.name)
        assert(entity.lat == model.lat)
        assert(entity.lon == model.lon)
        assert(entity.country == model.country)
        assert(entity.state == model.state)
    }

    @Test
    fun mapDomainToEntityTest() {
        val model = DirectTestModel.createDirect()
        val entity = directEntityMapper.mapToEntity(model)

        assert(model.name == entity.name)
        assert(model.lat == entity.lat)
        assert(model.lon == entity.lon)
        assert(model.country == entity.country)
        assert(model.state == entity.state)
    }


}