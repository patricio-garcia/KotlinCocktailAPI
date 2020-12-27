package cl.serlitoral.kotlincocktailapi.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Drink(
    @SerializedName("strDrinkThumb")
    val image: String = "",

    @SerializedName("strDrink")
    val name: String = "",

    @SerializedName("strInstructions")
    val description: String = "",

    @SerializedName("strAlcoholic")
    val hasAlcoholic: String = "Non_Alcoholic"

) : Parcelable

data class DrinkList(
    @SerializedName("drinks")
    val drinkList: List<Drink>
)
