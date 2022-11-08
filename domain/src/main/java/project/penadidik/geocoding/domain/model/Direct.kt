package project.penadidik.geocoding.domain.model

data class Direct(
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String,
    val state: String
): Model()