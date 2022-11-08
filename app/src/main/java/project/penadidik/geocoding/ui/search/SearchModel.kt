package project.penadidik.geocoding.ui.search

import project.penadidik.geocoding.base.BaseModel

data class SearchModel(
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String,
    val state: String
): BaseModel()