package com.example.veryinterestingtest.app.di

import com.example.veryinterestingtest.data.network.client.NetworkClient
import com.example.veryinterestingtest.data.network.client.RetrofitNetworkClient
import com.example.veryinterestingtest.data.network.service.SerperApiService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val ACCESS_TOKEN = "becd76ebe912ab28d2bb64715ce960d11fcc1a87"
private const val BASE_URL = "https://google.serper.dev"
val dataModule = module {

    single<Retrofit> {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(get())
            .build()
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.run {
                    proceed(
                        request()
                            .newBuilder()
                            .addHeader("X-API-KEY", ACCESS_TOKEN)
                            .build()
                    )
                }
            }.build()
    }

    single<SerperApiService> {
        val retrofit: Retrofit = get()
        retrofit.create(SerperApiService::class.java)
    }

    single<NetworkClient> {
        RetrofitNetworkClient(get())
    }
}