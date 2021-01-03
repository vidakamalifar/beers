package com.fob.beers.databease

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fob.beers.dao.BeerDAO
import com.fob.beers.dao.Converters
import com.fob.beers.model.Beer
import com.fob.beers.model.FavoriteBeer

 @TypeConverters(Converters::class)
    @Database(entities = [Beer::class, FavoriteBeer::class], version = 1)
    abstract class AppDatabase : RoomDatabase(){
        abstract val beerDAO: BeerDAO?
}