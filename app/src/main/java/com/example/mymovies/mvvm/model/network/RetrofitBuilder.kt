package com.example.mymovies.mvvm.model.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {
    private const val BASE_URL = "http://www.omdbapi.com/"
    const val API_KEY : String = "e5311742"
    val requestInterceptor = Interceptor { chain ->

        val url = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("apikey", API_KEY)
            .build()
        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()

        return@Interceptor chain.proceed(request)
    }

    var gson: Gson? = GsonBuilder()
        .setLenient()
        .create()

    val loggingInterceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    val okHttpClient: OkHttpClient.Builder =
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).addInterceptor(requestInterceptor)

    val retrofitClient: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    val apiService: MovieApiService by lazy {
        retrofitClient
            .build()
            .create(MovieApiService::class.java)
    }

}