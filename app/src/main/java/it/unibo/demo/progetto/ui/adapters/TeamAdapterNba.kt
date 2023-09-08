package it.unibo.demo.progetto.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.unibo.demo.progetto.R
import it.unibo.demo.progetto.ui.items.TeamNba

class TeamAdapterNba(private var teams: List<TeamNba>) : RecyclerView.Adapter<TeamAdapterNba.TeamViewHolder>() {

    private var pinocchio = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_team_nba, parent, false)
        return TeamViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val currentTeam = teams[position]
        holder.bind(currentTeam)
    }

    override fun getItemCount() = teams.size

    inner class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val teamName: TextView = itemView.findViewById(R.id.team_nba)
        private val position: TextView = itemView.findViewById(R.id.position_nba)
        private val wins: TextView = itemView.findViewById(R.id.wins)
        private val losses: TextView = itemView.findViewById(R.id.losses)
        private val percentage: TextView = itemView.findViewById(R.id.percentage)

        fun bind(team: TeamNba) {
            val rows = team.rows

            // Verifica se ci sono righe nella squadra
            if (rows.isNotEmpty()) {
                // Assicurati che pinocchio non superi la dimensione delle righe
                if (pinocchio >= rows.size) {
                    pinocchio = 0 // Torna a 0 se supera la dimensione
                }

                // Prendi la prima riga della squadra
                val row = rows[pinocchio]

                teamName.text = row.team.get("name").asString
                position.text = row.position.toString()
                wins.text = row.wins.toString()
                losses.text = row.losses.toString()
                percentage.text = row.percentage

                pinocchio++
            }
        }
    }

    fun updateData(newTeams: List<TeamNba>) {
        teams = newTeams
        notifyDataSetChanged()
    }
}
