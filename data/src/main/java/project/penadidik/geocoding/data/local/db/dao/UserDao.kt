package project.penadidik.geocoding.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import project.penadidik.geocoding.data.model.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userEntity: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(userEntities: List<UserEntity>)

    @Query("UPDATE user SET favorite=:favorite WHERE id = :id")
    fun setFavorite(id: Int, favorite: Int)

    @Query("SELECT * FROM user")
    fun getAll(): List<UserEntity>

    @Query("SELECT * FROM user WHERE favorite = 1")
    fun getAllFavorites(): List<UserEntity>

    @Query("SELECT * FROM user WHERE id = :id")
    fun findById(id: Int): Single<UserEntity>

    @Query("SELECT * FROM user WHERE login LIKE '%' || :query || '%' ORDER BY CASE WHEN :isAsc = 1 THEN login END ASC, CASE WHEN :isAsc = 0 THEN login END DESC")
    fun search(query: String, isAsc: Boolean): Single<List<UserEntity>>

    @Query("DELETE FROM user")
    fun nukeTable()
}