package com.fob.beers.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorite")
data class FavoriteBeer(
    @PrimaryKey
    var id: Int?,
    @ColumnInfo
    var name: String?,
    @ColumnInfo
    @SerializedName("tagline")
    var tagLine: String?,
    @ColumnInfo
    @SerializedName("image_url")
    var imageUrl: String?,
    @ColumnInfo
    var isFavorite : Boolean? = false

)
