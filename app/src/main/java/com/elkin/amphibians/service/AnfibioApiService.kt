package com.elkin.amphibians.service

import com.elkin.amphibians.model.Anfibio
import retrofit2.http.GET

interface AnfibioApiService {

   @GET("amphibians")
   suspend fun getAnfibios(): List<Anfibio>
}