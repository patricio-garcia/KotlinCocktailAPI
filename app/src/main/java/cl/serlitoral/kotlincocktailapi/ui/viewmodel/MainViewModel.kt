package cl.serlitoral.kotlincocktailapi.ui.viewmodel

import androidx.lifecycle.*
import cl.serlitoral.kotlincocktailapi.data.model.DrinkEntity
import cl.serlitoral.kotlincocktailapi.domain.iRepo
import cl.serlitoral.kotlincocktailapi.vo.Resourse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repo: iRepo): ViewModel() {

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

    fun saveDrink(drink: DrinkEntity) {
        viewModelScope.launch {
            repo.insertDrink(drink)
        }
    }

    fun getFavoriteDrinks() = liveData(Dispatchers.IO) {
        emit(Resourse.Loading())
        try {
            emit(repo.getFavoriteDrinks())
        } catch (e: Exception) {
            emit(Resourse.Failure(e))
        }
    }
}