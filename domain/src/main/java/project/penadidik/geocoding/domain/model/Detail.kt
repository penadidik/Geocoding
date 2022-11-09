package project.penadidik.geocoding.domain.model

class Detail : Model() {
    var dt: Int? = null
    var main: Main? = null
    var weather: List<Weather>? = arrayListOf()
    var clouds: Clouds? = null
    var wind: Wind? = null
    var pop: Double? = null
    var visibility: Int? = null
    var dt_txt: String? = null
    var lat: Double? = null
    var lon: Double? = null
    var isFavorite: Boolean? = false
}