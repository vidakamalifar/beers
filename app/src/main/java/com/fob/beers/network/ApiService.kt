package com.fob.beers.network

import com.fob.beers.model.Beer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

class ApiService {


    companion object {
        private const val BASE_URL = " https://api.punkapi.com/v2/"
    }

    private object Keys {
        const val ITEMS = "per_page"
        const val PAGE = "page"
    }

    object ApiHandler {

        private var retrofit: Retrofit

        init {

            //api handler called
            val okHttpClientBuilder = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)

            val basicLog = HttpLoggingInterceptor()
            basicLog.level = HttpLoggingInterceptor.Level.BASIC
            okHttpClientBuilder.addInterceptor(basicLog)

            val bodyLog = HttpLoggingInterceptor()
            bodyLog.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(bodyLog)

            val headerLog = HttpLoggingInterceptor()
            headerLog.level = HttpLoggingInterceptor.Level.HEADERS
            okHttpClientBuilder.addInterceptor(headerLog)

            retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClientBuilder.build())
                .build()
        }

        val retrofitService: ApiService by lazy {
            retrofit.create(ApiService::class.java)
        }
    }

    interface ApiService {
        @GET(ApiAddress.getBeers)
        fun getBeersList(
            @Query(Keys.PAGE) page: Int?,
            @Query(Keys.ITEMS) items: Int?
        ): Call<ArrayList<Beer?>>
    }
}