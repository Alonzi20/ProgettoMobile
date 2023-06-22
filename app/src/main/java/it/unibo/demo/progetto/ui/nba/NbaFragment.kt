package it.unibo.demo.progetto.ui.nba

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
import it.unibo.demo.progetto.databinding.FragmentGalleryBinding
import it.unibo.demo.progetto.databinding.FragmentNbaBinding

class NbaFragment : Fragment() {

    private var _binding: FragmentNbaBinding? = null

    private lateinit var giornatanbaViewModel: GiornataNbaViewModel
    private lateinit var classificanbaViewModel: ClassificaNbaViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        giornatanbaViewModel = ViewModelProvider(this).get(GiornataNbaViewModel::class.java)
        classificanbaViewModel = ViewModelProvider(this).get(ClassificaNbaViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val nbaViewModel =
            ViewModelProvider(this).get(NbaViewModel::class.java)

        _binding = FragmentNbaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        nbaViewModel.text.observe(viewLifecycleOwner) {
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
                        val fragment = GiornataNba()
                        fragment.viewModel = giornatanbaViewModel
                        childFragmentManager.beginTransaction()
                            .replace(R.id.sub_fragment_container, fragment)
                            .commit()
                    }
                    1 -> {
                        val fragment = ClassificaNba()
                        fragment.viewModel = classificanbaViewModel
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
        val fragment = GiornataNba()
        fragment.viewModel = giornatanbaViewModel
        childFragmentManager.beginTransaction()
            .replace(R.id.sub_fragment_container, fragment)
            .commit()
    }
}