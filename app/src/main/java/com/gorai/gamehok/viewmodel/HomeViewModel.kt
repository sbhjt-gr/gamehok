package com.gorai.gamehok.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gorai.gamehok.R
import com.gorai.gamehok.data.Game
import com.gorai.gamehok.data.Tournament
import com.gorai.gamehok.api.GamesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

sealed class GamesUiState {
    object Loading : GamesUiState()
    data class Success(val games: List<Game>) : GamesUiState()
    data class Error(val message: String) : GamesUiState()
}

sealed class TournamentsUiState {
    object Loading : TournamentsUiState()
    data class Success(val tournaments: List<Tournament>) : TournamentsUiState()
    data class Error(val message: String) : TournamentsUiState()
}

class HomeViewModel : ViewModel() {
    private val _gamesUiState = MutableStateFlow<GamesUiState>(GamesUiState.Loading)
    val gamesUiState: StateFlow<GamesUiState> = _gamesUiState.asStateFlow()

    private val _tournamentsUiState = MutableStateFlow<TournamentsUiState>(TournamentsUiState.Loading)
    val tournamentsUiState: StateFlow<TournamentsUiState> = _tournamentsUiState.asStateFlow()

    private val api = Retrofit.Builder()
        .baseUrl("https://67c9566f0acf98d07089d293.mockapi.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GamesApi::class.java)

    private val gameImages = mapOf(
        "BGMI" to R.drawable.img536,
        "FREE_FIRE" to R.drawable.img28,
        "COD_MOBILE" to R.drawable.img528
    )

    init {
        fetchGames()
        fetchTournaments()
    }

    private fun fetchGames() {
        viewModelScope.launch {
            _gamesUiState.value = GamesUiState.Loading
            try {
                val fetchedGames = api.getGames()
                _gamesUiState.value = GamesUiState.Success(
                    fetchedGames.map { game ->
                        game.copy(imagePath = gameImages[game.gameName]?.toString() ?: "")
                    }
                )
            } catch (e: Exception) {
                _gamesUiState.value = GamesUiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }

    private fun fetchTournaments() {
        viewModelScope.launch {
            _tournamentsUiState.value = TournamentsUiState.Loading
            try {
                val tournaments = api.getTournaments()
                _tournamentsUiState.value = TournamentsUiState.Success(tournaments)
            } catch (e: Exception) {
                _tournamentsUiState.value = TournamentsUiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}