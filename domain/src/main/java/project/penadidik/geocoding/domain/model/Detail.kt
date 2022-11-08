package project.penadidik.geocoding.domain.model

data class DetailGeoCoding (
    val dt: Int,
    val main: Main? = null,
    val weather: List<Weather>? = arrayListOf(),
    val clouds: Clouds? = null,
    val wind: Wind? = null,
    val pop: Int,
    val visibility: Int,
    val dt_txt: String
): Model()