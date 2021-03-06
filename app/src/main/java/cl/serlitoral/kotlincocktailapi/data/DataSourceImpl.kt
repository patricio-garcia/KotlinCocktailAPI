package cl.serlitoral.kotlincocktailapi.data

import cl.serlitoral.kotlincocktailapi.AppDatabase
import cl.serlitoral.kotlincocktailapi.data.model.Drink
import cl.serlitoral.kotlincocktailapi.data.model.DrinkEntity
import cl.serlitoral.kotlincocktailapi.domain.data.iDataSource
import cl.serlitoral.kotlincocktailapi.vo.Resourse
import cl.serlitoral.kotlincocktailapi.vo.RetrofitClient

class DataSourceImpl(private val appDatabase: AppDatabase): iDataSource {

    override suspend fun getDrinkByName(drinkName: String): Resourse<List<Drink>> {
        return Resourse.Success(RetrofitClient.webservice.getDrinkByName(drinkName).drinkList)
    }

    override suspend fun insertDrinkIntoRoom(drink: DrinkEntity) {
        appDatabase.drinkDAO().insertFavorite(drink)
    }

    override suspend fun getFavoriteDrinks(): Resourse<List<DrinkEntity>> {
        return Resourse.Success(appDatabase.drinkDAO().getAllFavoriteDrinks())
    }
}