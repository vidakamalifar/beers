package com.fob.beers.dao

import androidx.room.TypeConverter
import com.fob.beers.model.DefaultValue
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converters {

    @TypeConverter
    fun appToString(app: DefaultValue): String = Gson().toJson(app)

    @TypeConverter
    fun stringToApp(string: String): DefaultValue = Gson().fromJson(string, DefaultValue::class.java)

    @TypeConverter
    fun fromString(value: String?): ArrayList<String?>? {
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}