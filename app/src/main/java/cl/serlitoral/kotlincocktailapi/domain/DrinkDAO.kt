package cl.serlitoral.kotlincocktailapi.domain

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.serlitoral.kotlincocktailapi.data.model.DrinkEntity

interface DrinkDAO {
    @Query("SELECT * FROM DrinkEntity")
    suspend fun getAllFavoriteDrinks(): List<DrinkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(drink: DrinkEntity)
}