package project.penadidik.geocoding.domain

import project.penadidik.geocoding.domain.model.DirectGeoCoding

object GeoCodingTestModel {

    fun createList(): ArrayList<DirectGeoCoding> {
        val list = ArrayList<DirectGeoCoding>()

        list.add(DirectGeoCoding(
            name = "Australia",
            lat = -31.95260295,
            lon = 152.55512735272188,
            country = "AU",
            state = "New South Wales"
        ))

        list.add(DirectGeoCoding(
            name = "Australia",
            lat = -12.7892966,
            lon = -66.5223197,
            country = "BO",
            state = "Beni"
        ))

        list.add(DirectGeoCoding(
            name = "Australia",
            lat = 22.500905,
            lon = -81.1356126,
            country = "CU",
            state = "Matanzas"
        ))

        return list

    }

}