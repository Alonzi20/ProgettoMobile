package it.unibo.demo.progetto.ui.serie_a.subfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import it.unibo.demo.progetto.databinding.FragmentClassificaSerieABinding

class ClassificaSerieA : Fragment()  {
    private var _binding: FragmentClassificaSerieABinding? = null

    internal lateinit var viewModel: ClassificaViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val classificaSerieAViewModel =
            ViewModelProvider(this).get(ClassificaViewModel::class.java)

        _binding = FragmentClassificaSerieABinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textClassificaSerieA
        classificaSerieAViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}