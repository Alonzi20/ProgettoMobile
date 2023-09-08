package it.unibo.demo.progetto.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.unibo.demo.progetto.R
import it.unibo.demo.progetto.ui.items.Match

class MatchAdapterNba(private var matches: List<Match>) : RecyclerView.Adapter<MatchAdapterNba.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_match_nba, parent, false)
        return MatchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val currentMatch = matches[position]
        holder.bind(currentMatch)
    }

    override fun getItemCount() = matches.size

    inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val status: TextView = itemView.findViewById(R.id.statusNba)
        private val homeTeam: TextView = itemView.findViewById(R.id.homeTeamNba)
        private val homeScore: TextView = itemView.findViewById(R.id.homeScoreNba)
        private val awayTeam: TextView = itemView.findViewById(R.id.awayTeamNba)
        private val awayScore: TextView = itemView.findViewById(R.id.awayScoreNba)

        fun bind(match: Match) {
            status.text = match.status.get("description").asString
            homeTeam.text = match.homeTeam.get("name").asString
            homeScore.text = match.homeScore.get("display").asString
            awayTeam.text = match.awayTeam.get("name").asString
            awayScore.text = match.awayScore.get("display").asString
            // Imposta le altre informazioni della partita sugli elementi grafici appropriati
        }
    }

    fun updateData(newMatches: List<Match>) {
        matches = newMatches
        notifyDataSetChanged()
    }
}