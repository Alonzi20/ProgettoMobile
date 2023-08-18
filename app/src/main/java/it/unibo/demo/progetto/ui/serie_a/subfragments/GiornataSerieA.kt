package it.unibo.demo.progetto.ui.serie_a.subfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.unibo.demo.progetto.App
import it.unibo.demo.progetto.R
import it.unibo.demo.progetto.databinding.FragmentGiornataSerieABinding
import it.unibo.demo.progetto.ui.adapters.MatchAdapter
import it.unibo.demo.progetto.util.SerieAData

class GiornataSerieA : Fragment()  {
    private var _binding: FragmentGiornataSerieABinding? = null

    internal lateinit var viewModel: GiornataViewModel

    private lateinit var roundSpinner: Spinner
    private lateinit var serieAData: SerieAData

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //ViewModelProvider(this).get(GiornataViewModel::class.java)

        _binding = FragmentGiornataSerieABinding.inflate(inflater, container, false)
        val rootView = binding.root

        // Recupera il riferimento alla tendina
        roundSpinner = rootView.findViewById(R.id.roundSpinner)

        // Crea l'adapter per popolare la tendina con gli elementi dall'array delle risorse
        val roundAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.round_numbers, R.layout.spinner_item)
        roundAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        roundSpinner.adapter = roundAdapter

        // Aggiungi il listener per gestire la selezione dell'utente
        roundSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Recupera il numero selezionato
                val selectedRound = parent?.getItemAtPosition(position).toString().toInt()

                serieAData = SerieAData(App.footApiService)

                val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerView)
                val matchAdapter = MatchAdapter(emptyList())
                recyclerView.adapter = matchAdapter
                recyclerView.layoutManager = LinearLayoutManager(requireContext())

                serieAData.getMatchesData(selectedRound) { matchList ->
                    if (matchList != null && matchList.isNotEmpty()) {
                        matchAdapter.updateData(matchList)

                        recyclerView.visibility = View.VISIBLE
                        binding.textGiornataSerieA.visibility = View.GONE
                    } else {
                        recyclerView.visibility = View.GONE
                        binding.textGiornataSerieA.visibility = View.VISIBLE
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Fai qualcosa se l'utente non seleziona nulla
            }
        }

        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}