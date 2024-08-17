package com.elkin.amphibians.ui.theme.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.elkin.amphibians.R
import com.elkin.amphibians.model.Anfibio

@Composable
fun HomeApp(
    anfibioUiState: AnfibioUiState,
    retryAction: () -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    when (anfibioUiState) {
        is AnfibioUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is AnfibioUiState.Success -> PantallaAnfibios(anfibioUiState.anfibios)
        is AnfibioUiState.Error -> ErrorScreen(retryAction, modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = "fallo", modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text("Reintentar")
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = "cargando"
    )
}

@Composable
fun PantallaAnfibios(anfibios: List<Anfibio>) {
    LazyColumn(modifier = Modifier.padding(top = 95.dp)) {
        items(anfibios) { anfibio -> CardAnfibios(anfibio) }
    }
}

@Composable
fun CardAnfibios(anfibio: Anfibio) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(2.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        Column {
            Row(modifier = Modifier.padding(4.dp)) {
                Text(text = anfibio.name)
                Text(text = " (${anfibio.type})")
            }
            AsyncImage(
                model = anfibio.imgSrc,
                contentDescription = anfibio.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()

            )
            Text(
                text = anfibio.description,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(4.dp)
            )
        }
    }
}

