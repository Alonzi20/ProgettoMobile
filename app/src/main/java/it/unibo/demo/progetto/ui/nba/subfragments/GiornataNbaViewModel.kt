package it.unibo.demo.progetto.ui.nba.subfragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GiornataNbaViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Giornata Nba Subfragment"
    }
    val text: LiveData<String> = _text
}