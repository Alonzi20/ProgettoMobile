package it.unibo.demo.progetto.ui.nba.subfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import it.unibo.demo.progetto.databinding.FragmentGiornataNbaBinding

class GiornataNba : Fragment() {
    private var _binding: FragmentGiornataNbaBinding? = null

    internal lateinit var viewModel: GiornataNbaViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val giornataNbaViewModel =
            ViewModelProvider(this).get(GiornataNbaViewModel::class.java)

        _binding = FragmentGiornataNbaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGiornataNba
        giornataNbaViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}