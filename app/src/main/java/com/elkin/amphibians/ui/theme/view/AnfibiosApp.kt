package com.elkin.amphibians.ui.theme.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun AmfibiosApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { AppBarPerzonalizada() }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val anfibioViewModel: AnfibioViewModel = viewModel(factory = AnfibioViewModel.Factory)

            HomeApp(
                anfibioUiState = anfibioViewModel.anfibioUiState,
                retryAction= anfibioViewModel::getAnfibios,
                contentPadding = it
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarPerzonalizada() {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "Amfibios")
        })
}