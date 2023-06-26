package it.unibo.demo.progetto.ui.serie_a

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SerieAViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Serie A Fragment"
    }
    val text: LiveData<String> = _text
}