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
import it.unibo.demo.progetto.databinding.FragmentClassificaSerieABinding
import it.unibo.demo.progetto.ui.adapters.MatchAdapter
import it.unibo.demo.progetto.ui.adapters.TeamAdapter
import it.unibo.demo.progetto.util.SerieAData

class ClassificaSerieA : Fragment()  {
    private var _binding: FragmentClassificaSerieABinding? = null

    internal lateinit var viewModel: ClassificaViewModel

    private lateinit var serieAData: SerieAData

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ViewModelProvider(this).get(ClassificaViewModel::class.java)

        _binding = FragmentClassificaSerieABinding.inflate(inflater, container, false)
        val rootView = binding.root

        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerViewClassificaSerieA)
        val teamAdapter = TeamAdapter(emptyList())
        recyclerView.adapter = teamAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        serieAData = SerieAData(App.footApiService)

        serieAData.getLeagueTotalStandings { teamList ->
            if (teamList != null && teamList.isNotEmpty()) {
                teamAdapter.updateData(teamList)

                recyclerView.visibility = View.VISIBLE
                binding.textClassificaSerieA.visibility = View.GONE
            } else {
                recyclerView.visibility = View.GONE
                binding.textClassificaSerieA.visibility = View.VISIBLE
            }
        }

        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}