package com.fob.beers.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DefaultValue (
    @PrimaryKey(autoGenerate = true)
    var id : Int?,
    @ColumnInfo
    var value : Double?,
    @ColumnInfo
    var unit : String? = null

)

