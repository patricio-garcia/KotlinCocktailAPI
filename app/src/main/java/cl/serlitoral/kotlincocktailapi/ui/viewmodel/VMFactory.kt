package cl.serlitoral.kotlincocktailapi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.serlitoral.kotlincocktailapi.domain.iRepo

class VMFactory(private val repo: iRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(iRepo::class.java).newInstance(repo)
    }
}