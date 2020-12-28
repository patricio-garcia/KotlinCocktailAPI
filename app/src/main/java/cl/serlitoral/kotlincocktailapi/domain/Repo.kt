package cl.serlitoral.kotlincocktailapi.domain

import cl.serlitoral.kotlincocktailapi.data.model.Drink
import cl.serlitoral.kotlincocktailapi.data.model.DrinkEntity
import cl.serlitoral.kotlincocktailapi.vo.Resourse

interface Repo {
    suspend fun getDrinkList(drinkName: String): Resourse<List<Drink>>
    suspend fun getFavoriteDrinks(): Resourse<List<DrinkEntity>>
    suspend fun insertDrink(drink: DrinkEntity)
}