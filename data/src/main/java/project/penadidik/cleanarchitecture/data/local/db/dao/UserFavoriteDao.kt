package project.penadidik.cleanarchitecture.data.local.db.dao

import androidx.room.*
import project.penadidik.cleanarchitecture.data.model.UserFavoriteEntity
import io.reactivex.Single

@Dao
interface UserFavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userEntity: UserFavoriteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(userEntities: List<UserFavoriteEntity>)

    @Query("SELECT * FROM user_favorite")
    fun getAll(): Single<List<UserFavoriteEntity>>

    @Query("SELECT * FROM user_favorite")
    fun getAllFavorites(): List<UserFavoriteEntity>

    @Query("SELECT * FROM user_favorite WHERE id = :id")
    fun findById(id: Int): Single<UserFavoriteEntity>

    @Query("SELECT * FROM user_favorite WHERE id = :id")
    fun getById(id: Int): UserFavoriteEntity

    @Query("SELECT * FROM user_favorite WHERE login LIKE '%' || :query || '%'")
    fun search(query: String): Single<List<UserFavoriteEntity>>

    @Query("DELETE FROM user_favorite")
    fun nukeTable()

    @Delete
    fun delete(model: UserFavoriteEntity)
}