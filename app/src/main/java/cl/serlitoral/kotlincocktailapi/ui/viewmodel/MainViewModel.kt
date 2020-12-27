package cl.serlitoral.kotlincocktailapi.ui.viewmodel

import androidx.lifecycle.*
import cl.serlitoral.kotlincocktailapi.domain.Repo
import cl.serlitoral.kotlincocktailapi.vo.Resourse
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repo: Repo): ViewModel() {

    private val drinkData = MutableLiveData<String>()

    fun setDrink(drinkName: String) {
        drinkData.value = drinkName
    }

    init {
        setDrink("margarita")
    }

    val fetchDrinkList = drinkData.distinctUntilChanged().switchMap { drink ->

        liveData(Dispatchers.IO) {
            emit(Resourse.Loading())
            try {
                emit(repo.getDrinkList(drink))
            } catch (e: Exception) {
                emit(Resourse.Failure(e))
            }
        }
    }
}