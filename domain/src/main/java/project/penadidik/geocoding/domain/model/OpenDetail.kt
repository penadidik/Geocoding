package project.penadidik.geocoding.domain.model

data class OpenDetail (
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<DetailGeoCoding>? = arrayListOf()
): Model()