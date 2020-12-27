package cl.serlitoral.kotlincocktailapi.vo

import java.lang.Exception

sealed class Resourse<out T> {
    class Loading<out T>: Resourse<T>()
    data class Success<out T>(val data: T): Resourse<T>()
    data class Failure(val exception: Exception): Resourse<Nothing>()
}
