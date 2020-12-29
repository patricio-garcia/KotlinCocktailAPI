package cl.serlitoral.kotlincocktailapi.ui.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import cl.serlitoral.kotlincocktailapi.AppDatabase
import cl.serlitoral.kotlincocktailapi.R
import cl.serlitoral.kotlincocktailapi.data.DataSourceImpl
import cl.serlitoral.kotlincocktailapi.data.model.Drink
import cl.serlitoral.kotlincocktailapi.data.model.DrinkEntity
import cl.serlitoral.kotlincocktailapi.domain.RepoImpl
import cl.serlitoral.kotlincocktailapi.ui.viewmodel.MainViewModel
import cl.serlitoral.kotlincocktailapi.ui.viewmodel.VMFactory
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_drink_details.*


class DrinkDetailsFragment : Fragment() {

    private lateinit var drink: Drink

    private val viewModel by viewModels<MainViewModel> {
        VMFactory(RepoImpl(
            DataSourceImpl(AppDatabase.getDatabase(requireActivity().applicationContext))
        )
    )}

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
        return inflater.inflate(R.layout.fragment_drink_details, container, false)
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

        btn_Favorite.setOnClickListener {
            viewModel.saveDrink(DrinkEntity(drink.drinkId, drink.image, drink.name, drink.description, drink.hasAlcoholic))
            Toast.makeText(requireContext(), "Se guardó en Favoritos", Toast.LENGTH_LONG).show()
        }

    }

}