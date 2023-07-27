package it.unibo.demo.progetto.util

import it.unibo.demo.progetto.interfaces.FootApiService
import it.unibo.demo.progetto.ui.items.Match
import retrofit2.Call
import retrofit2.Response

class SerieAData (private val footApiService: FootApiService) {
    fun getMatchesData(roundNumber: Int, callback: (List<Match>?) -> Unit) {
        val call = footApiService.getLeagueEventsByRound(roundNumber)
        call.enqueue(object : retrofit2.Callback<List<Match>> {
            override fun onResponse(call: Call<List<Match>>, response: Response<List<Match>>) {
                if (response.isSuccessful) {
                    val matchList = response.body()
                    callback(matchList)
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<List<Match>>, t: Throwable) {
                callback(null)
            }
        })
    }
}