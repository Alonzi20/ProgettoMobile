package it.unibo.demo.progetto.interfaces

import it.unibo.demo.progetto.ui.items.MatchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface FootApiService {

    @Headers("X-RapidAPI-Key: 17b28db6ccmsh43e9535e75e46f0p19df4cjsn6f99146b4863", "X-RapidAPI-Host: footapi7.p.rapidapi.com")
    @GET("tournament/23/season/42415/matches/round/{round_number}")
    fun getLeagueEventsByRound(
        @Path("round_number") roundNumber: Int
    ): Call<MatchResponse>
}