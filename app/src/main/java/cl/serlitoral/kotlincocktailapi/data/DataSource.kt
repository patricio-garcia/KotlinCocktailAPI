package cl.serlitoral.kotlincocktailapi.data

import cl.serlitoral.kotlincocktailapi.data.model.Drink
import cl.serlitoral.kotlincocktailapi.vo.Resourse
import cl.serlitoral.kotlincocktailapi.vo.RetrofitClient

class DataSource {

    suspend fun getDrinkByName(drinkName: String): Resourse<List<Drink>> {
        return Resourse.Success(RetrofitClient.webservice.getDrinkByName(drinkName).drinkList)
    }
}