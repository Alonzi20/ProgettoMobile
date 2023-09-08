package it.unibo.demo.progetto.ui.adapters

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.unibo.demo.progetto.R
import it.unibo.demo.progetto.ui.items.Team

class TeamAdapter(private var teams: List<Team>) : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    private var pinocchio = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
        return TeamViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val currentTeam = teams[position]
        holder.bind(currentTeam)
    }

    override fun getItemCount() = teams.size

    inner class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val teamName: TextView = itemView.findViewById(R.id.team)
        private val position: TextView = itemView.findViewById(R.id.position)
        private val points: TextView = itemView.findViewById(R.id.points)
        private val matches: TextView = itemView.findViewById(R.id.matches)
        private val teamColorsView: ImageView = itemView.findViewById(R.id.teamColorsView)

        fun bind(team: Team) {
            val rows = team.rows

            // Verifica se ci sono righe nella squadra
            if (rows.isNotEmpty()) {
                // Assicurati che pinocchio non superi la dimensione delle righe
                if (pinocchio >= rows.size) {
                    pinocchio = 0 // Torna a 0 se supera la dimensione
                }

                // Prendi la prima riga della squadra
                val row = rows[pinocchio]

                // Imposta il colore del background per lo stemma della squadra di casa
                val colors = row.teamColors
                if (colors != null) {
                    val primaryColor = Color.parseColor(colors.primary)
                    val secondaryColor = Color.parseColor(colors.secondary)
                    val gradientDrawable = GradientDrawable()
                    gradientDrawable.shape = GradientDrawable.OVAL
                    gradientDrawable.orientation = GradientDrawable.Orientation.LEFT_RIGHT
                    gradientDrawable.colors = intArrayOf(
                        primaryColor,
                        secondaryColor,
                        primaryColor,
                        secondaryColor,
                        primaryColor,
                        secondaryColor,
                        primaryColor
                    )
                    teamColorsView.background = gradientDrawable
                }

                teamName.text = row.team.get("name").asString
                position.text = row.position.toString()
                points.text = row.points.toString()
                matches.text = row.matches.toString()

                pinocchio++
            }
        }
    }

    fun updateData(newTeams: List<Team>) {
        teams = newTeams
        notifyDataSetChanged()
    }
}
