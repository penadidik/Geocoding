package project.penadidik.geocoding.data

import project.penadidik.geocoding.data.model.DirectEntity
import project.penadidik.geocoding.domain.model.Direct

object DirectTestModel {

    fun createDirectEntity(): DirectEntity = DirectEntity(
        name = "Australia",
        lat = -31.95260295,
        lon = 152.55512735272188,
        country = "AU",
        state = "New South Wales"
    )

    fun createDirect(): Direct = Direct(
        name = "Australia",
        lat = -12.7892966,
        lon = -66.5223197,
        country = "BO",
        state = "Beni"
    )

}