package cl.serlitoral.kotlincocktailapi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import cl.serlitoral.kotlincocktailapi.AppDatabase
import cl.serlitoral.kotlincocktailapi.R
import cl.serlitoral.kotlincocktailapi.data.DataSource
import cl.serlitoral.kotlincocktailapi.data.model.Drink
import cl.serlitoral.kotlincocktailapi.domain.RepoImpl
import cl.serlitoral.kotlincocktailapi.ui.viewmodel.MainViewModel
import cl.serlitoral.kotlincocktailapi.ui.viewmodel.VMFactory
import cl.serlitoral.kotlincocktailapi.vo.Resourse
import kotlinx.android.synthetic.main.fragment_favorites.*


class FavoritesFragment : Fragment(), MainAdapter.OnDrinkClickListener {

    private val viewModel by viewModels<MainViewModel> {
        VMFactory(
            RepoImpl(
            DataSource(AppDatabase.getDatabase(requireActivity().applicationContext))
        )
    )}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        rv_favoriteDrinks.layoutManager = LinearLayoutManager(requireContext())
        rv_favoriteDrinks.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    private fun setupObservers() {
        viewModel.getFavoriteDrinks().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resourse.Loading -> { }

                is Resourse.Success -> {
                    //Se convierte drinkEntity a una drinkList
                    val drinkList = result.data.map {
                        Drink(it.drinkId, it.image, it.name, it.description, it.hasAlcoholic)
                    }

                    rv_favoriteDrinks.adapter = MainAdapter(requireContext(), drinkList, this)
                }

                else -> { }
            }
        })
    }

    override fun onDrinkClick(drink: Drink) {

    }
}