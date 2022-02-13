package br.com.josef.marvelcharacters.data.remote


import br.com.josef.marvelcharacters.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {
    private const val BASE_URL = "https://gateway.marvel.com:443/v1/public/"
    private val httpLoggingInterceptor = HttpLoggingInterceptor()

    private var retrofit: Retrofit? = null
        get() {
            if (field == null) {
                val httpClient = OkHttpClient.Builder()
                httpClient.readTimeout(30, TimeUnit.SECONDS)
                httpClient.connectTimeout(30, TimeUnit.SECONDS)
                httpClient.writeTimeout(30, TimeUnit.SECONDS)

                if (BuildConfig.DEBUG) {
                    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                    httpClient.addInterceptor(httpLoggingInterceptor)
                }
                field = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()
            }
            return field
        }
    val apiService: API
        get() = retrofit!!.create(API::class.java)
}