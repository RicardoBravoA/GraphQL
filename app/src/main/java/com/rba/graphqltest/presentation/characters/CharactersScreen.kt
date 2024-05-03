package com.rba.graphqltest.presentation.characters

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rba.graphqltest.domain.model.CharacterModel

@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel,
    paddingValues: PaddingValues,
    onClick: (item: CharacterModel) -> Unit
) {

    val uiState by viewModel.viewState.collectAsState()
    val context = LocalContext.current

    when (uiState) {
        is CharactersViewModel.ViewState.Loading -> {
            LoadingIndicator()
        }

        is CharactersViewModel.ViewState.Data -> {
            val data = (uiState as CharactersViewModel.ViewState.Data).data
            CharactersList(data, paddingValues, onClick)
        }

        is CharactersViewModel.ViewState.Error -> {
            Toast.makeText(
                context,
                (uiState as CharactersViewModel.ViewState.Error).message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

@Composable
fun CharactersList(
    charactersList: List<CharacterModel>,
    paddingValues: PaddingValues,
    onClick: (item: CharacterModel) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {
        items(items = charactersList, key = { it.id }) {
            CharacterItem(item = it, onClick)
        }
    }
}

@Composable
fun CharacterItem(item: CharacterModel, onClick: (item: CharacterModel) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(200.dp)
            .clickable { onClick(item) },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = item.image,
                contentDescription = item.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .align(Alignment.BottomCenter)
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {
                Text(
                    text = item.name,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxSize(),
                    style = TextStyle(fontSize = 20.sp)
                )
            }
        }
    }
}

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
