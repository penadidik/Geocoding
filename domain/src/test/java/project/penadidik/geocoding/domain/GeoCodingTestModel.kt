package project.penadidik.geocoding.domain

import project.penadidik.geocoding.domain.model.*

object GeoCodingTestModel {

    fun createList(): ArrayList<Direct> {
        val list = ArrayList<Direct>()

        list.add(Direct(
            name = "Australia",
            lat = -31.95260295,
            lon = 152.55512735272188,
            country = "AU",
            state = "New South Wales"
        ))

        list.add(Direct(
            name = "Australia",
            lat = -12.7892966,
            lon = -66.5223197,
            country = "BO",
            state = "Beni"
        ))

        list.add(Direct(
            name = "Australia",
            lat = 22.500905,
            lon = -81.1356126,
            country = "CU",
            state = "Matanzas"
        ))

        return list

    }

    fun createDetail(): List<Detail> {
        val main = Main(
            temp = 302.66,
            feels_like = 304.56,
            temp_min = 301.65,
            temp_max = 302.66,
            pressure = 1013,
            sea_level = 1013,
            grnd_level = 1014,
            humidity = 57,
            temp_kf = 1.01
        )

        val weatherList = ArrayList<Weather>()

        val weather = Weather(
            id = 800,
            main = "Clear",
            description = "clear sky",
            icon = "01n"
        )
        weatherList.add(weather)

        val clouds = Clouds(all = 5)

        val wind = Wind(
            speed =  6.72,
            deg = 282,
            gust = 7.65
        )

        val detailList = ArrayList<Detail>()
        val detail = Detail(
            dt = 1667746800,
            main = main,
            weather = weatherList,
            clouds = clouds,
            wind = wind,
            visibility = 10000,
            pop = 0,
            dt_txt = "2022-11-06 15:00:00"
        )

        detailList.add(detail)
        return detailList
    }

}