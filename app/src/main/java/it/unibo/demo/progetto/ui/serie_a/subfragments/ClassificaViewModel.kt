package it.unibo.demo.progetto.ui.serie_a.subfragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ClassificaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Classifica Serie A Subfragment"
    }
    val text: LiveData<String> = _text
}