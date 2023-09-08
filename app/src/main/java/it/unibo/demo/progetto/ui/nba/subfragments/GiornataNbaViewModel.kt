package it.unibo.demo.progetto.ui.nba.subfragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.unibo.demo.progetto.App
import it.unibo.demo.progetto.ui.items.Match
import it.unibo.demo.progetto.ui.items.MatchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class GiornataNbaViewModel : ViewModel() {
    private val _liveMatch = MutableLiveData<List<Match>>()

    fun fetchMatchSchedules(day: Int, month: Int, year: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = App.basketApiService.getMatchSchedules(day, month, year).execute() // Esegui la tua chiamata API per ottenere i live matches

                // Verifica se la risposta è valida
                if (response.isSuccessful) {
                    val liveMatches = response.body()?.events ?: emptyList() // Ottieni la lista di partite live dalla risposta o una lista vuota se è null
                    _liveMatch.postValue(liveMatches) // Aggiorna il LiveData con la lista di partite live
                } else {
                    // Gestisci eventuali errori di rete
                    Log.e("GiornataViewModel", "API call failed: ${response.code()}")
                }
            } catch (e: Exception) {
                // Gestisci eventuali eccezioni
                Log.e("GiornataViewModel", "API call error: ${e.message}")
            }
        }
    }

    fun startPeriodicDataUpdate(day: Int, month: Int, year: Int) {
        val timer = Timer()
        val updateInterval = 3600000L // Intervallo di aggiornamento in millisecondi (1 ora)

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                fetchMatchSchedules(day, month, year)
            }
        }, 0, updateInterval)
    }
}