package it.unibo.demo.progetto.ui.items

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class TeamNba(
    @SerializedName("rows")
    var rows: List<RowNba>,
    val name: String
)

data class RowNba (
    val team: JsonObject,
    val position: Int,
    val wins: Int,
    val losses: Int,
    val percentage: String
)

data class TeamResponseNba(
    @SerializedName("standings")
    @JvmSuppressWildcards
    val standings: List<TeamNba>
)