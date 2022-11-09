package project.penadidik.geocoding.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import project.penadidik.geocoding.data.base.ModelEntity

@Entity(tableName = "direct")
class DirectEntity: ModelEntity() {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var name: String? = null
    var lat: Double? = null
    var lon: Double? = null
    var country: String? = null
    var state: String? = null
}