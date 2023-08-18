package it.unibo.demo.progetto.util

import android.util.Log
import it.unibo.demo.progetto.interfaces.FootApiService
import it.unibo.demo.progetto.ui.items.Match
import it.unibo.demo.progetto.ui.items.MatchResponse
import it.unibo.demo.progetto.ui.items.TeamColors
import retrofit2.Call
import retrofit2.Response

class SerieAData (private val footApiService: FootApiService) {
    fun getMatchesData(roundNumber: Int, callback: (List<Match>?) -> Unit) {
        val call = footApiService.getLeagueEventsByRound(roundNumber)
        call.enqueue(object : retrofit2.Callback<MatchResponse> {
            override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                if (response.isSuccessful) {
                    val matchResponse = response.body()
                    if (matchResponse != null) {
                        //val matches: List<Match> = matchResponse.events

                        val matches: List<Match> = matchResponse.events.map { match ->
                            val homeTeamColorsJson = match.homeTeam.get("teamColors").asJsonObject
                            val awayTeamColorsJson = match.awayTeam.get("teamColors").asJsonObject
                            val homeTeamColors = TeamColors(
                                homeTeamColorsJson.get("primary").asString,
                                homeTeamColorsJson.get("secondary").asString
                            )
                            val awayTeamColors = TeamColors(
                                awayTeamColorsJson.get("primary").asString,
                                awayTeamColorsJson.get("secondary").asString
                            )
                            match.apply {
                                this.homeTeamColors = homeTeamColors
                                this.awayTeamColors = awayTeamColors
                            }
                        }
                        /*
                        for (match in matches) {
                            // Ottieni i nomi delle squadre
                            val homeTeamName = match.homeTeam.get("name").asString
                            val awayTeamName = match.awayTeam.get("name").asString

                            // Ottieni i punteggi 'display' come interi
                            val homeScoreDisplay = match.homeScore.get("display").asInt
                            val awayScoreDisplay = match.awayScore.get("display").asInt

                            val homeTeamColors = match.homeTeamColors
                            val awayTeamColors = match.awayTeamColors

                        }

                         */

                        // Qui hai la lista di oggetti Match, puoi assegnarla al callback
                        callback(matches)
                    } else {
                        callback(null)
                    }
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                println("API Response: nada")
                Log.e("SerieAData", "API call failed: ${t.message}")
                callback(null)
            }
        })
    }
}