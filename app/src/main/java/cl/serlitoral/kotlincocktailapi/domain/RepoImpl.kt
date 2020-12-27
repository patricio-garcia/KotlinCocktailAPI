package cl.serlitoral.kotlincocktailapi.domain

import cl.serlitoral.kotlincocktailapi.data.DataSource
import cl.serlitoral.kotlincocktailapi.data.model.Drink
import cl.serlitoral.kotlincocktailapi.vo.Resourse

class RepoImpl(private val dataSource: DataSource): Repo {

    override suspend fun getDrinkList(drinkName: String): Resourse<List<Drink>> {
        return dataSource.getDrinkByName(drinkName)
    }
}