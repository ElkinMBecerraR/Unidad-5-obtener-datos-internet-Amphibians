package com.elkin.amphibians.data

import com.elkin.amphibians.model.Anfibio
import com.elkin.amphibians.service.AnfibioApiService

interface AnfibioRepository {
    suspend fun getAnfibios(): List<Anfibio>
}

class NetworkAnfibioRepository(private val anfibioApiService: AnfibioApiService) :
    AnfibioRepository {
    override suspend fun getAnfibios(): List<Anfibio> = anfibioApiService.getAnfibios()
}