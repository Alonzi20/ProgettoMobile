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
import it.unibo.demo.progetto.ui.items.Match

class MatchAdapter(private var matches: List<Match>) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        return MatchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val currentMatch = matches[position]
        holder.bind(currentMatch)
    }

    override fun getItemCount() = matches.size

    inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val status: TextView = itemView.findViewById(R.id.status)
        private val homeTeam: TextView = itemView.findViewById(R.id.homeTeam)
        private val homeScore: TextView = itemView.findViewById(R.id.homeScore)
        private val awayTeam: TextView = itemView.findViewById(R.id.awayTeam)
        private val awayScore: TextView = itemView.findViewById(R.id.awayScore)
        private val homeTeamColorsView: ImageView = itemView.findViewById(R.id.homeTeamColorsView)
        private val awayTeamColorsView: ImageView = itemView.findViewById(R.id.awayTeamColorsView)
        fun bind(match: Match) {
            // Imposta il colore del background per lo stemma della squadra di casa
            val homeColors = match.homeTeamColors
            if (homeColors != null) {
                val homePrimaryColor = Color.parseColor(homeColors.primary)
                val homeSecondaryColor = Color.parseColor(homeColors.secondary)
                val homeGradientDrawable = GradientDrawable()
                homeGradientDrawable.shape = GradientDrawable.OVAL
                homeGradientDrawable.orientation = GradientDrawable.Orientation.LEFT_RIGHT
                homeGradientDrawable.colors = intArrayOf(homePrimaryColor, homeSecondaryColor, homePrimaryColor, homeSecondaryColor, homePrimaryColor, homeSecondaryColor, homePrimaryColor)
                homeTeamColorsView.background = homeGradientDrawable
            }

            // Imposta il colore del background per lo stemma della squadra in trasferta
            val awayColors = match.awayTeamColors
            if (awayColors != null) {
                val awayPrimaryColor = Color.parseColor(awayColors.primary)
                val awaySecondaryColor = Color.parseColor(awayColors.secondary)
                val awayGradientDrawable = GradientDrawable()
                awayGradientDrawable.shape = GradientDrawable.OVAL
                awayGradientDrawable.orientation = GradientDrawable.Orientation.LEFT_RIGHT
                awayGradientDrawable.colors = intArrayOf(awayPrimaryColor, awaySecondaryColor, awayPrimaryColor, awaySecondaryColor, awayPrimaryColor, awaySecondaryColor, awayPrimaryColor)
                awayTeamColorsView.background = awayGradientDrawable
            }
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