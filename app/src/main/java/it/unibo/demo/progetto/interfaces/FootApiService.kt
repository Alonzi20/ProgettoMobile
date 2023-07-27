package it.unibo.demo.progetto.interfaces

import it.unibo.demo.progetto.ui.items.Match
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FootApiService {

    @Headers("X-RapidAPI-Key: 17b28db6ccmsh43e9535e75e46f0p19df4cjsn6f99146b4863")
    @GET("/leagues/23/rounds/{round_number}")
    fun getLeagueEventsByRound(
        @Query("round_number") roundNumber: Int
    ): Call<List<Match>>
}