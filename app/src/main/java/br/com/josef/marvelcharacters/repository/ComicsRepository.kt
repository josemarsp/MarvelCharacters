package br.com.josef.marvelcharacters.repository

import br.com.josef.marvelcharacters.data.remote.RetrofitService
import br.com.josef.marvelcharacters.model.dataclass.ComicsResponse
import io.reactivex.Observable

class ComicsRepository {
    fun getComics(
        format: String, formatType: String,
        noVariants: Boolean, orderBy: String, ts: String,
        hash: String, apikey: String
    ): Observable<ComicsResponse> {
        return RetrofitService.apiService
            .getComics(format, formatType, noVariants, orderBy, ts, hash, apikey)
    }

    fun getCharacters(
        orderBy: String, ts: String,
        hash: String, apikey: String
    ): Observable<ComicsResponse> {
        return RetrofitService.apiService.getCharacters(orderBy, ts, hash, apikey)
    }
}