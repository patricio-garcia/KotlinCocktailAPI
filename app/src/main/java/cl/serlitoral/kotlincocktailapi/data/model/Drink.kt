package cl.serlitoral.kotlincocktailapi.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Drink(
    val image: String = "",
    val name: String = "",
    val description: String = ""
) : Parcelable
