package com.example.veryinterestingtest.app.di

import com.example.veryinterestingtest.data.network.client.NetworkClient
import com.example.veryinterestingtest.data.network.client.RetrofitNetworkClient
import com.example.veryinterestingtest.data.network.service.SerperApiService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val ACCESS_TOKEN = "518adabb09d791c234f39ad9e857e4097463bab0"
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