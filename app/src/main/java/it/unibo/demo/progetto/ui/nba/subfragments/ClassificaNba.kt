package it.unibo.demo.progetto.ui.nba.subfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.unibo.demo.progetto.App
import it.unibo.demo.progetto.R
import it.unibo.demo.progetto.databinding.FragmentClassificaNbaBinding
import it.unibo.demo.progetto.ui.adapters.TeamAdapterNba
import it.unibo.demo.progetto.util.NbaData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ClassificaNba : Fragment() {
    private var _binding: FragmentClassificaNbaBinding? = null

    internal lateinit var viewModel: ClassificaNbaViewModel

    private lateinit var nbaData: NbaData

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ViewModelProvider(this).get(ClassificaNbaViewModel::class.java)

        _binding = FragmentClassificaNbaBinding.inflate(inflater, container, false)
        val rootView = binding.root

        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerViewClassificaNba)
        val teamAdapter = TeamAdapterNba(emptyList())
        recyclerView.adapter = teamAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        nbaData = NbaData(App.basketApiService)
        GlobalScope.launch(Dispatchers.IO) {
            nbaData.getLeagueTotalStandings { teamList ->
                activity?.runOnUiThread {
                    if (teamList != null && teamList.isNotEmpty()) {
                        teamAdapter.updateData(teamList)

                        recyclerView.visibility = View.VISIBLE
                        binding.textClassificaNba.visibility = View.GONE
                    } else {
                        recyclerView.visibility = View.GONE
                        binding.textClassificaNba.visibility = View.VISIBLE
                    }
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