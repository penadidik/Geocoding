package project.penadidik.cleanarchitecture.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import project.penadidik.cleanarchitecture.data.local.db.dao.UserDao
import project.penadidik.cleanarchitecture.data.local.db.dao.UserFavoriteDao
import project.penadidik.cleanarchitecture.data.model.UserEntity
import project.penadidik.cleanarchitecture.data.model.UserFavoriteEntity

@Database(entities = [UserEntity::class, UserFavoriteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userFavoriteDao(): UserFavoriteDao
}