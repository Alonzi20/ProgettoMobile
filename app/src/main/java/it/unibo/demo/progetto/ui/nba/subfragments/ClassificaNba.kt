package it.unibo.demo.progetto.ui.nba.subfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class ClassificaNba : Fragment() {
    internal lateinit var viewModel: ClassificaNbaViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inizializza il ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(ClassificaNbaViewModel::class.java)

        // Resto del codice del frammento...
        return null
    }
}