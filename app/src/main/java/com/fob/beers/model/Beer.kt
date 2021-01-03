package com.fob.beers.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "beer")
data class Beer(

    @PrimaryKey
    var id: Int?,
    @ColumnInfo
    var name: String?,
    @ColumnInfo
    @SerializedName("tagline")
    var tagLine: String?,
    @ColumnInfo
    @SerializedName("first_brewed")
    var firstBrewed: String?,
    @ColumnInfo
    var description: String?,
    @ColumnInfo
    @SerializedName("image_url")
    var imageUrl: String?,
    @ColumnInfo
    var abv: Double?,
    @ColumnInfo
    var ibu: Double?,
    @ColumnInfo
    @SerializedName("target_fg")
    var targetFg: Double?,
    @ColumnInfo
    @SerializedName("target_og")
    var targetOg: Double?,
    @ColumnInfo
    var ebc: Double?,
    @ColumnInfo
    var srm: Double?,
    @ColumnInfo
    var ph: Double?,
    @ColumnInfo
    @SerializedName("attenuation_level")
    var attenuationLevel: Double?,
    @ColumnInfo
    var volume: DefaultValue,
    @ColumnInfo
    @SerializedName("food_pairing")
    var foodPairingList: ArrayList<String>?,
    @SerializedName("brewers_tips")
    var brewersTips: String?,
    @SerializedName("contributed_by")
    var contributedBy: String?,
    @ColumnInfo
    var isFavorite: Boolean = false

)

{
    constructor(name: String) : this(
        0,
        name,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        DefaultValue(0, 1.1, ""),
        null,
        null,
        null,
        false
    )
}

