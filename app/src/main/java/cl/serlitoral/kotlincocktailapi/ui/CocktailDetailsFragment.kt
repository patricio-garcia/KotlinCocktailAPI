package cl.serlitoral.kotlincocktailapi.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cl.serlitoral.kotlincocktailapi.R
import cl.serlitoral.kotlincocktailapi.data.model.Drink
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_cocktail_details.*


class CocktailDetailsFragment : Fragment() {

    private lateinit var drink: Drink

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireArguments().let {
            drink = it.getParcelable("drink")!!
            Log.d("Detalle_Frag", "$drink")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cocktail_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireContext())
            .load(drink.image)
            .centerCrop()
            .into(img_drinkDetail)

        tv_drinkTitle.text = drink.name

        if(drink.hasAlcoholic == "Non_Alcoholic") {
            tv_hasAlcoholic.text = "Bebida sin base alcohólica"
        } else {
            tv_hasAlcoholic.text = "Bebida con base alcohólica"
        }

        tv_DrinkInstructions.text = drink.description

    }

}