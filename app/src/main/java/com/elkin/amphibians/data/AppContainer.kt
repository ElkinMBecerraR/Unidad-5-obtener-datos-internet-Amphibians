package com.elkin.amphibians.data

import com.elkin.amphibians.service.AnfibioApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.Retrofit
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType


interface AppContainer{
    val anfibioRespository: AnfibioRepository
}

class DefaultAppContainer: AppContainer{
    private val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: AnfibioApiService by lazy{
        retrofit.create(AnfibioApiService::class.java)
    }

    override val anfibioRespository: AnfibioRepository by lazy{
        NetworkAnfibioRepository(retrofitService)
    }
}