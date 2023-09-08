package it.unibo.demo.progetto.util

import android.util.Log
import it.unibo.demo.progetto.interfaces.BasketApiService
import it.unibo.demo.progetto.ui.items.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

class NbaData (private val basketApiService: BasketApiService) {
    fun getMatchesData(day: Int, month: Int, year: Int, callback: (List<Match>?) -> Unit) {
        val call = basketApiService.getMatchSchedules(day, month, year)
        call.enqueue(object : retrofit2.Callback<MatchResponse> {
            override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                if (response.isSuccessful) {
                    val matchResponse = response.body()
                    if (matchResponse != null) {
                        val allMatches = matchResponse.events
                        val matches = allMatches.filter { it.tournament.name == "NBA" }

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
                Log.e("NbaData", "API call failed: ${t.message}")
                callback(null)
            }
        })
    }

    fun getLeagueTotalStandings(callback: (List<TeamNba>?) -> Unit) {
        val call = basketApiService.getLeagueTotalStandings()
        call.enqueue(object : retrofit2.Callback<TeamResponseNba> {
            override fun onResponse(
                call: Call<TeamResponseNba>,
                response: Response<TeamResponseNba>
            ) {
                if (response.isSuccessful) {
                    val teamResponse = response.body()
                    if (teamResponse != null) {
                        val teams: MutableList<TeamNba> =
                            mutableListOf() // Lista per accumulare le squadre

                        teamResponse.standings.forEach { team ->
                            team.rows.forEach { row ->
                                if (team.name == "NBA")
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

            override fun onFailure(call: Call<TeamResponseNba>, t: Throwable) {
                println("API Response: nada")
                Log.e("SerieAData", "API call failed: ${t.message}")
                callback(null)
            }
        })
    }
}