package it.unibo.demo.progetto.ui.nba.subfragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ClassificaNbaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Classifica Nba Subfragment"
    }
    val text: LiveData<String> = _text
}