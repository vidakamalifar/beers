package com.fob.beers.dao

import androidx.room.*
import com.fob.beers.model.Beer
import com.fob.beers.model.FavoriteBeer

@Dao
interface BeerDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(beers : ArrayList<Beer?>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteBeer(favoriteBeer : FavoriteBeer)

    @Update
    fun updateFavoriteBeer(vararg favoriteBeer: FavoriteBeer)

    @Query("SELECT * FROM beer")
    fun getBeers(): List<Beer?>?

    @Query("SELECT * FROM favorite")
    fun getFavoriteBeers(): List<FavoriteBeer>

    @Delete
    fun deleteFavoriteBeers(favoriteBeer : FavoriteBeer)

    @Query("SELECT * FROM beer WHERE id = :id")
    fun getBeerDetail(id: Int?): Beer?

    @Query("SELECT * FROM favorite WHERE id = :id")
    fun getFavoriteBeerDetail(id: Int?): FavoriteBeer?

}