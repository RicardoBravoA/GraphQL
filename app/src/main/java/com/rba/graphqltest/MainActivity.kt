package com.rba.graphqltest

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rba.graphqltest.domain.model.CharacterModel
import com.rba.graphqltest.presentation.characters.CharactersList
import com.rba.graphqltest.presentation.characters.CharactersScreen
import com.rba.graphqltest.presentation.characters.CharactersViewModel
import com.rba.graphqltest.ui.theme.GraphQLTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: CharactersViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GraphQLTestTheme {
                Scaffold(topBar = {
                    TopAppBar(title = {
                        Text("Rick and Morty")
                    })
                }) { paddingValues ->
                    CharactersScreen(viewModel = viewModel, paddingValues) {
                        Toast.makeText(baseContext, it.name, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        viewModel.getCharacters()
    }
}

@Preview(showBackground = true)
@Composable
fun CharactersListPreviewWithCharacters() {
    val characters = listOf(
        CharacterModel(
            id = "1", name = "Rick Sanchez", status = "Alive", gender = "", image = "", species = ""
        ), CharacterModel(
            id = "2", name = "Morty Smith", status = "Alive", gender = "", image = "", species = ""
        ), CharacterModel(
            id = "3", name = "Summer Smith", status = "Alive", gender = "", image = "", species = ""
        )
    )
    CharactersList(charactersList = characters, PaddingValues()) {}
}
