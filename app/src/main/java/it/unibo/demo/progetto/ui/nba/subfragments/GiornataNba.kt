package it.unibo.demo.progetto.ui.nba.subfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.unibo.demo.progetto.App
import it.unibo.demo.progetto.R
import it.unibo.demo.progetto.databinding.FragmentGiornataNbaBinding
import it.unibo.demo.progetto.ui.adapters.MatchAdapterNba
import it.unibo.demo.progetto.util.NbaData

class GiornataNba : Fragment() {
    private var _binding: FragmentGiornataNbaBinding? = null

    internal lateinit var viewModel: GiornataNbaViewModel

    private lateinit var daySpinner: Spinner
    private lateinit var monthSpinner: Spinner
    private lateinit var yearSpinner: Spinner
    private lateinit var searchButton: Button
    private lateinit var nbaData: NbaData
    private var day: Int = 8
    private var month: Int = 3
    private var year: Int = 2023

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val liveMatchesViewModel = ViewModelProvider(this).get(GiornataNbaViewModel::class.java)

        _binding = FragmentGiornataNbaBinding.inflate(inflater, container, false)
        val rootView = binding.root

        // Recupera il riferimento alla tendina dei giorni
        daySpinner = rootView.findViewById(R.id.daySpinner)

        // Crea l'adapter per popolare la tendina con gli elementi dall'array delle risorse
        val dayAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.days, R.layout.spinner_item)
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        daySpinner.adapter = dayAdapter

        // Aggiungi il listener per gestire la selezione dell'utente
        daySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Recupera il numero selezionato
                day = parent?.getItemAtPosition(position).toString().toInt()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Fai qualcosa se l'utente non seleziona nulla
            }
        }

        // Recupera il riferimento alla tendina dei mesi
        monthSpinner = rootView.findViewById(R.id.monthSpinner)

        // Crea l'adapter per popolare la tendina con gli elementi dall'array delle risorse
        val monthAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.months, R.layout.spinner_item)
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        monthSpinner.adapter = monthAdapter

        // Aggiungi il listener per gestire la selezione dell'utente
        monthSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Recupera il numero selezionato
                month = parent?.getItemAtPosition(position).toString().toInt()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Fai qualcosa se l'utente non seleziona nulla
            }
        }

        // Recupera il riferimento alla tendina degli anni
        yearSpinner = rootView.findViewById(R.id.yearSpinner)

        // Crea l'adapter per popolare la tendina con gli elementi dall'array delle risorse
        val yearAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.years, R.layout.spinner_item)
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        yearSpinner.adapter = yearAdapter

        // Aggiungi il listener per gestire la selezione dell'utente
        yearSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Recupera il numero selezionato
                year = parent?.getItemAtPosition(position).toString().toInt()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Fai qualcosa se l'utente non seleziona nulla
            }
        }

        liveMatchesViewModel.startPeriodicDataUpdate(day, month, year)

        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerViewnba)
        val matchAdapter = MatchAdapterNba(emptyList())
        recyclerView.adapter = matchAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Recupera il riferimento alla tendina degli anni
        searchButton = rootView.findViewById(R.id.search)

        searchButton.setOnClickListener {
            nbaData = NbaData(App.basketApiService)

            nbaData.getMatchesData(day, month, year) { matchList ->
                if (matchList != null && matchList.isNotEmpty()) {
                    matchAdapter.updateData(matchList)

                    recyclerView.visibility = View.VISIBLE
                    binding.textGiornataNba.visibility = View.GONE
                } else {
                    recyclerView.visibility = View.GONE
                    binding.textGiornataNba.visibility = View.VISIBLE
                }
            }
        }

        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}