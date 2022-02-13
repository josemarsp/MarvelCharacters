package br.com.josef.marvelcharacters.repository

import br.com.josef.marvelcharacters.data.remote.RetrofitService
import br.com.josef.marvelcharacters.model.dataclass.BaseRequest
import io.reactivex.Observable

class Repository {
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
        orderBy: String, ts: String, hash: String, apikey: String
    ): Observable<BaseRequest> {
        return RetrofitService.apiService.getCharacters(orderBy, ts, hash, apikey)
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

}