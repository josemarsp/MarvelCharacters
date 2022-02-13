package br.com.josef.marvelcharacters.data.remote

import retrofit2.http.GET
import br.com.josef.marvelcharacters.model.dataclass.BaseRequest
import io.reactivex.Observable
import retrofit2.http.Path
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
    ): Observable<BaseRequest>

    @GET("characters?")
    fun getCharacters(
        @Query("orderBy") orderBy: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("apikey") apikey: String
    ): Observable<BaseRequest>

    @GET("characters/{id}/comics?")
    fun getComicsCharacters(
        @Path("id") id: Int,
        @Query("format") format: String,
        @Query("orderBy") orderBy: String,
        @Query("limit") limit: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("apikey") apikey: String
    ): Observable<BaseRequest>

    @GET("characters/{id}/series?")
    fun getSeriesCharacters(
        @Path("id") id: Int,
        @Query("contains") contains: String,
        @Query("orderBy") orderBy: String,
        @Query("limit") limit: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("apikey") apikey: String
    ): Observable<BaseRequest>
}