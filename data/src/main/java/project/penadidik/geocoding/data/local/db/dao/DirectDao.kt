package project.penadidik.geocoding.data.local.db.dao

import androidx.room.*
import io.reactivex.Single
import project.penadidik.geocoding.data.local.model.DirectEntity

@Dao
interface DirectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(directEntity: DirectEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(directEntities: List<DirectEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(directEntity: DirectEntity)

    @Query("SELECT * FROM direct")
    fun getAll(): Single<List<DirectEntity>>

    @Query("SELECT * FROM direct WHERE lat = :lat AND lon = :lon")
    fun findBy(lat: Double, lon: Double): Single<DirectEntity>

    @Query("SELECT * FROM direct WHERE lat = :lat AND lon = :lon")
    fun getBy(lat: Double, lon: Double): DirectEntity

    @Query("DELETE FROM direct")
    fun nukeTable()

    @Delete
    fun delete(model: DirectEntity)
}