package cl.serlitoral.kotlincocktailapi.domain

import androidx.room.*
import cl.serlitoral.kotlincocktailapi.data.model.Drink
import cl.serlitoral.kotlincocktailapi.data.model.DrinkEntity

@Dao
interface DrinkDAO {
    @Query("SELECT * FROM DrinkEntity")
    suspend fun getAllFavoriteDrinks(): List<DrinkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(drink: DrinkEntity)
}