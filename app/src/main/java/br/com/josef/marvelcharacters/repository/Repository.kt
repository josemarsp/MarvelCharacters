package br.com.josef.marvelcharacters.repository

import androidx.lifecycle.LiveData
import br.com.josef.marvelcharacters.data.remote.RetrofitService
import br.com.josef.marvelcharacters.model.dao.MarvelResultDao
import br.com.josef.marvelcharacters.model.dataclass.BaseRequest
import br.com.josef.marvelcharacters.model.dataclass.MarvelResult
import io.reactivex.Observable
import org.koin.core.module.Module

class Repository(private val marvelResultDao: MarvelResultDao) {
    fun getComics(
        format: String,
        formatType: String,
        noVariants: Boolean,
        orderBy: String,
        ts: String,
        hash: String,
        apikey: String
    ): Observable<BaseRequest> {
        return RetrofitService.apiService
            .getComics(format, formatType, noVariants, orderBy, ts, hash, apikey)
    }

    fun getCharacters(
        orderBy: String, limit: Int, offset: Int, ts: String, hash: String, apikey: String
    ): Observable<BaseRequest> {
        return RetrofitService.apiService.getCharacters(orderBy, limit, offset, ts, hash, apikey)
    }

    fun getComicsCharacters(
        id: Int,
        format: String,
        orderBy: String,
        limit: String,
        ts: String,
        hash: String,
        apikey: String
    ): Observable<BaseRequest> {
        return RetrofitService.apiService
            .getComicsCharacters(id, format, orderBy, limit, ts, hash, apikey)
    }

    fun getSeriesCharacters(
        id: Int,
        contains: String,
        orderBy: String,
        limit: String,
        ts: String,
        hash: String,
        apikey: String
    ): Observable<BaseRequest> {
        return RetrofitService.apiService
            .getSeriesCharacters(id, contains, orderBy, limit, ts, hash, apikey)
    }

    val favoriteCharacters: LiveData<MutableList<MarvelResult>>
        get() = marvelResultDao.getAll()

    suspend fun insertFavorite(favorite: MarvelResult) {
        marvelResultDao.insert(favorite)
    }

    suspend fun deleteFavorite(favorite: MarvelResult) {
        marvelResultDao.delete(favorite)
    }

    suspend fun updateFavorite(favorite: MarvelResult) {
        marvelResultDao.update(favorite)
    }

}