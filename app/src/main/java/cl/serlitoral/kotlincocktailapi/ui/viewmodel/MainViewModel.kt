package cl.serlitoral.kotlincocktailapi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import cl.serlitoral.kotlincocktailapi.domain.Repo
import cl.serlitoral.kotlincocktailapi.vo.Resourse
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repo: Repo): ViewModel() {

    val fetchDrinkList = liveData(Dispatchers.IO) {
        emit(Resourse.Loading())
        try {
            emit(repo.getDrinkList())
        } catch (e: Exception) {
            emit(Resourse.Failure(e))
        }
    }
}