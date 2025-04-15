package com.gorai.gamehok.api

import com.gorai.gamehok.data.Game
import com.gorai.gamehok.data.Tournament
import retrofit2.http.GET

interface GamesApi {
    @GET("games")
    suspend fun getGames(): List<Game>

    @GET("tournaments")
    suspend fun getTournaments(): List<Tournament>
}