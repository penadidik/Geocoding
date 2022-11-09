package project.penadidik.geocoding.data.local.db.dao

import androidx.room.*
import io.reactivex.Single
import project.penadidik.geocoding.data.local.model.DetailEntity
import project.penadidik.geocoding.data.local.model.DirectEntity
import project.penadidik.geocoding.domain.model.Detail

@Dao
interface DetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(detailEntity: DetailEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(detailEntities: List<DetailEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(detailEntity: DetailEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAll(detailEntity: List<DetailEntity>)

    @Query("SELECT * FROM detail")
    fun getAll(): Single<List<DetailEntity>>

    @Query("SELECT * FROM detail WHERE lat = :lat AND lon = :lon")
    fun findBy(lat: Double, lon: Double): Single<DetailEntity>

    @Query("SELECT * FROM detail WHERE lat = :lat AND lon = :lon AND dt_txt = :dtTxt")
    fun getBy(lat: Double, lon: Double, dtTxt: String): DetailEntity

    @Query("DELETE FROM detail")
    fun nukeTable()

    @Delete
    fun delete(model: DetailEntity)
}