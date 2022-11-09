package project.penadidik.geocoding.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import project.penadidik.geocoding.data.local.db.dao.DetailDao
import project.penadidik.geocoding.data.local.db.dao.DirectDao
import project.penadidik.geocoding.data.local.model.DetailEntity
import project.penadidik.geocoding.data.local.model.DirectEntity

@Database(entities = [DirectEntity::class, DetailEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun directDao(): DirectDao
    abstract fun detailDao(): DetailDao
}