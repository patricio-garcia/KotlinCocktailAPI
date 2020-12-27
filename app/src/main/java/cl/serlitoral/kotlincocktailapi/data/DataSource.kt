package cl.serlitoral.kotlincocktailapi.data

import cl.serlitoral.kotlincocktailapi.data.model.Drink
import cl.serlitoral.kotlincocktailapi.vo.Resourse

class DataSource {
    private val generateDrinkList = listOf(
        Drink("https://cdn5.recetasdeescandalo.com/wp-content/uploads/2018/09/Coctel-margarita-como-prepararlo.-Receta-e-ingredientes.jpg", "Margartita", "Con azucar, vidka y nueces"),
        Drink("https://www.recetas-argentinas.com/base/stock/Recipe/2-image/2-image_web.jpg", "Fernet", "Fernet con coca y 2 hielos"),
        Drink("https://pbs.twimg.com/media/CERSHJwXIAATqjl.jpg", "Toro", "Toto con pritty"),
        Drink("https://64.media.tumblr.com/2a00c67fe2becf9e6704245c2432e625/tumblr_ny82d7tHAT1u916tro1_640.jpg", "Gancia", "Gancia con sprite")
    )

    fun getDrinkList(): Resourse<List<Drink>> {
        return Resourse.Success(generateDrinkList)
    }
}