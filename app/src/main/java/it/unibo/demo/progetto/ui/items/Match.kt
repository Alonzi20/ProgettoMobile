package it.unibo.demo.progetto.ui.items

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class Match(
    val status: JsonObject,
    val homeTeam: JsonObject,
    val awayTeam: JsonObject,
    val homeScore: JsonObject,
    val awayScore: JsonObject,
    var homeTeamColors: TeamColors,
    var awayTeamColors: TeamColors
) {
    fun setScore() {

    }
}

data class MatchResponse(
    @SerializedName("events")
    @JvmSuppressWildcards
    val events: List<Match>
)

data class TeamColors(
    val primary: String,
    val secondary: String
)