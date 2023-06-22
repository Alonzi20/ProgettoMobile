package it.unibo.demo.progetto.ui.serie_a

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import it.unibo.demo.progetto.R
import it.unibo.demo.progetto.databinding.FragmentSerieABinding
import it.unibo.demo.progetto.ui.serie_a.subfragments.ClassificaSerieA
import it.unibo.demo.progetto.ui.serie_a.subfragments.ClassificaViewModel
import it.unibo.demo.progetto.ui.serie_a.subfragments.GiornataSerieA
import it.unibo.demo.progetto.ui.serie_a.subfragments.GiornataViewModel

class SerieAFragment : Fragment() {

    private var _binding: FragmentSerieABinding? = null

    private lateinit var giornataViewModel: GiornataViewModel
    private lateinit var classificaViewModel: ClassificaViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        giornataViewModel = ViewModelProvider(this).get(GiornataViewModel::class.java)
        classificaViewModel = ViewModelProvider(this).get(ClassificaViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val serieAViewModel =
            ViewModelProvider(this).get(SerieAViewModel::class.java)

        _binding = FragmentSerieABinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        serieAViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupDropdownMenu(view: View) {
        val dropdownMenu = view.findViewById<Spinner>(R.id.dropdown_menu)
        val adapter = ArrayAdapter.createFromResource(requireContext(), R.array.dropdown_items, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dropdownMenu.adapter = adapter

        dropdownMenu.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        val fragment = GiornataSerieA()
                        fragment.viewModel = giornataViewModel
                        childFragmentManager.beginTransaction()
                            .replace(R.id.sub_fragment_container, fragment)
                            .commit()
                    }
                    1 -> {
                        val fragment = ClassificaSerieA()
                        fragment.viewModel = classificaViewModel
                        childFragmentManager.beginTransaction()
                            .replace(R.id.sub_fragment_container, fragment)
                            .commit()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nessuna azione necessaria quando non viene selezionata alcuna voce
            }
        }
    }

    private fun showInitialSubFragment() {
        val fragment = GiornataSerieA()
        fragment.viewModel = giornataViewModel
        childFragmentManager.beginTransaction()
            .replace(R.id.sub_fragment_container, fragment)
            .commit()
    }
}