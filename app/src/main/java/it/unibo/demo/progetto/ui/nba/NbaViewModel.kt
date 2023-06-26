package it.unibo.demo.progetto.ui.nba

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NbaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Nba Fragment"
    }
    val text: LiveData<String> = _text
}