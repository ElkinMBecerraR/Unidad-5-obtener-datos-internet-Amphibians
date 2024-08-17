package com.elkin.amphibians.ui.theme.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.elkin.amphibians.AnfibiosApplication
import com.elkin.amphibians.data.AnfibioRepository
import com.elkin.amphibians.model.Anfibio
import kotlinx.coroutines.launch
import java.io.IOException
import retrofit2.HttpException


sealed interface AnfibioUiState {
    data class Success(val anfibios: List<Anfibio>) : AnfibioUiState
    object Error : AnfibioUiState
    object Loading : AnfibioUiState
}

class AnfibioViewModel(private val anfibioRepository: AnfibioRepository) : ViewModel() {

    var anfibioUiState: AnfibioUiState by mutableStateOf(AnfibioUiState.Loading)
        private set

    init {
        getAnfibios()
    }

    fun getAnfibios() {
        viewModelScope.launch {
            anfibioUiState = AnfibioUiState.Loading
            anfibioUiState = try {
                val listaAnfibios = anfibioRepository.getAnfibios()
                AnfibioUiState.Success(anfibioRepository.getAnfibios())
            } catch (e: IOException) {
                AnfibioUiState.Error
            } catch (e: HttpException){
                AnfibioUiState.Error
            }
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AnfibiosApplication)
                val anfibioRepository = application.container.anfibioRespository
                AnfibioViewModel(anfibioRepository = anfibioRepository)
            }
        }
    }
}