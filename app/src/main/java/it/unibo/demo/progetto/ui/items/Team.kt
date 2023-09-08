package it.unibo.demo.progetto.ui.items

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class Team(
    @SerializedName("rows")
    var rows: List<Row>
)

data class Row (
    val team: JsonObject,
    val position: Int,
    val points: Int,
    val matches: Int,
    var teamColors: TeamColors
)

data class TeamResponse(
    @SerializedName("standings")
    @JvmSuppressWildcards
    val standings: List<Team>
)