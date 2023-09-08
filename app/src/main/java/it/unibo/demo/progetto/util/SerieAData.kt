package it.unibo.demo.progetto.util

import android.util.Log
import it.unibo.demo.progetto.interfaces.FootApiService
import it.unibo.demo.progetto.ui.items.*
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

    fun getLeagueTotalStandings(callback: (List<Team>?) -> Unit) {
        val call = footApiService.getLeagueTotalStandings()
        call.enqueue(object : retrofit2.Callback<TeamResponse> {
            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                if (response.isSuccessful) {
                    val teamResponse = response.body()
                    if (teamResponse != null) {
                        val teams: MutableList<Team> = mutableListOf() // Lista per accumulare le squadre

                        teamResponse.standings.forEach { team ->
                            team.rows.forEach { row ->
                                val teamColorsJson = row.team.get("teamColors").asJsonObject

                                val teamColors = TeamColors(
                                    teamColorsJson.get("primary").asString,
                                    teamColorsJson.get("secondary").asString
                                )
                                team.apply { row.teamColors = teamColors }

                                teams.add(team) // Aggiungi la squadra alla lista
                            }
                        }

                        // Qui hai la lista di oggetti Match, puoi assegnarla al callback
                        callback(teams)
                    } else {
                        callback(null)
                    }
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                println("API Response: nada")
                Log.e("SerieAData", "API call failed: ${t.message}")
                callback(null)
            }
        })
    }
}