package it.unibo.demo.progetto.ui.serie_a.subfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import it.unibo.demo.progetto.databinding.FragmentGiornataSerieABinding

class GiornataSerieA : Fragment()  {
    private var _binding: FragmentGiornataSerieABinding? = null

    internal lateinit var viewModel: GiornataViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val giornataSerieAViewModel =
            ViewModelProvider(this).get(GiornataViewModel::class.java)

        _binding = FragmentGiornataSerieABinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGiornataSerieA
        giornataSerieAViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}