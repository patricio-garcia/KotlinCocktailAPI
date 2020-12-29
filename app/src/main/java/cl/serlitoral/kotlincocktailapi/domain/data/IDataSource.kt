package cl.serlitoral.kotlincocktailapi.domain.data

import cl.serlitoral.kotlincocktailapi.data.model.Drink
import cl.serlitoral.kotlincocktailapi.data.model.DrinkEntity
import cl.serlitoral.kotlincocktailapi.vo.Resourse

interface iDataSource {

    suspend fun getDrinkByName(drinkName: String): Resourse<List<Drink>>
    suspend fun insertDrinkIntoRoom(drink: DrinkEntity)
    suspend fun getFavoriteDrinks(): Resourse<List<DrinkEntity>>
}