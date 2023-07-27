package it.unibo.demo.progetto.ui.serie_a.subfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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

    private lateinit var serieAData: SerieAData

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ViewModelProvider(this).get(GiornataViewModel::class.java)

        _binding = FragmentGiornataSerieABinding.inflate(inflater, container, false)
        val rootView = binding.root

        serieAData = SerieAData(App.footApiService)

        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerView)
        val matchAdapter = MatchAdapter(emptyList())
        recyclerView.adapter = matchAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        serieAData.getMatchesData(1) { matchList ->
            if (matchList != null && matchList.isNotEmpty()) {
                matchAdapter.updateData(matchList)

                recyclerView.visibility = View.VISIBLE
                binding.textGiornataSerieA.visibility = View.GONE
            } else {
                recyclerView.visibility = View.GONE
                binding.textGiornataSerieA.visibility = View.VISIBLE
            }
        }

        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}