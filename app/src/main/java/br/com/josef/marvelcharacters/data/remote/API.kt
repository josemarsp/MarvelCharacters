package br.com.josef.marvelcharacters.data.remote

import retrofit2.http.GET
import br.com.josef.marvelcharacters.model.dataclass.ComicsResponse
import io.reactivex.Observable
import retrofit2.http.Query

interface API {
    @GET("comics?")
    fun getComics(
        @Query("format") format: String,
        @Query("formatType") formatType: String,
        @Query("noVariants") noVariants: Boolean,
        @Query("orderBy") orderBy: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("apikey") apikey: String
    ): Observable<ComicsResponse>

    @GET("characters?")
    fun getCharacters(
        @Query("orderBy") orderBy: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("apikey") apikey: String
    ): Observable<ComicsResponse>
}