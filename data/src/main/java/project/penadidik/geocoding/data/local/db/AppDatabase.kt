package project.penadidik.geocoding.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import project.penadidik.geocoding.data.local.db.dao.UserDao
import project.penadidik.geocoding.data.local.db.dao.UserFavoriteDao
import project.penadidik.geocoding.data.model.UserEntity
import project.penadidik.geocoding.data.model.UserFavoriteEntity

@Database(entities = [UserEntity::class, UserFavoriteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userFavoriteDao(): UserFavoriteDao
}