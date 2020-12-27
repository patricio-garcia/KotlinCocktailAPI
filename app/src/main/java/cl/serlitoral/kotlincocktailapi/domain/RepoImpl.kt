package cl.serlitoral.kotlincocktailapi.domain

import cl.serlitoral.kotlincocktailapi.data.DataSource
import cl.serlitoral.kotlincocktailapi.data.model.Drink
import cl.serlitoral.kotlincocktailapi.vo.Resourse

class RepoImpl(private val dataSource: DataSource): Repo {

    override fun getDrinkList(): Resourse<List<Drink>> {
        return dataSource.generateDrinkList
    }
}