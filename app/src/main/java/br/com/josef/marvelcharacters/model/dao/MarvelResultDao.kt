package br.com.josef.marvelcharacters.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.josef.marvelcharacters.model.dataclass.MarvelResult

@Dao
interface MarvelResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (marvelResult: MarvelResult)

    @Delete
    suspend fun delete (marvelResult: MarvelResult)

    @Update
    suspend fun update (marvelResult: MarvelResult)

    //query
    @Query("SELECT * FROM table_favorites")
    fun getAll() : LiveData<MutableList<MarvelResult>>

}