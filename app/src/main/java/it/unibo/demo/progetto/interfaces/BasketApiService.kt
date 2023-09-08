package it.unibo.demo.progetto.interfaces

import it.unibo.demo.progetto.ui.items.MatchResponse
import it.unibo.demo.progetto.ui.items.TeamResponseNba
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface BasketApiService {
    @Headers("X-RapidAPI-Key: 17b28db6ccmsh43e9535e75e46f0p19df4cjsn6f99146b4863", "X-RapidAPI-Host: basketapi1.p.rapidapi.com")
    @GET("matches/{day}/{month}/{year}")
    fun getMatchSchedules(
        @Path("day") day: Int,
        @Path("month") month: Int,
        @Path("year") year: Int
    ): Call<MatchResponse>

    @Headers("X-RapidAPI-Key: ccfad3d332msh2007c32f4c6b027p138844jsnff606b547e6e", "X-RapidAPI-Host: basketapi1.p.rapidapi.com")
    @GET("tournament/132/season/45096/standings/total")
    fun getLeagueTotalStandings(): Call<TeamResponseNba>
}