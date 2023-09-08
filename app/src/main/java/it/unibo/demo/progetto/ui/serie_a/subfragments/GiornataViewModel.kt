package it.unibo.demo.progetto.ui.serie_a.subfragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.unibo.demo.progetto.App.Companion.footApiService
import it.unibo.demo.progetto.ui.items.Match
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

class GiornataViewModel : ViewModel() {

    private val _liveMatch = MutableLiveData<List<Match>>()

    fun fetchMatchesByRound(round: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val matches = footApiService.getLeagueEventsByRound(round).execute()
                if (matches.isSuccessful) {
                    val matchList = matches.body()?.events ?: emptyList()
                    _liveMatch.postValue(matchList)
                } else {
                    // Gestisci eventuali errori di rete
                    Log.e("GiornataViewModel", "API call failed: ${matches.code()}")
                }
            } catch (e: Exception) {
                // Gestisci eventuali eccezioni
                Log.e("GiornataViewModel", "API call error: ${e.message}")
            }
        }
    }

    fun startPeriodicDataUpdate(round: Int) {
        val timer = Timer()
        val updateInterval = 3600000L // Intervallo di aggiornamento in millisecondi (1 ora)

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                fetchMatchesByRound(round)
            }
        }, 0, updateInterval)
    }
}
