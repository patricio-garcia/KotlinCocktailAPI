package cl.serlitoral.kotlincocktailapi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.*
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
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
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), MainAdapter.OnDrinkClickListener {

    //Se inyecta dependencia al Model
    private val viewModel by viewModels<MainViewModel> {
        VMFactory(RepoImpl(
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
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSerachView()
        setupObservers()

        btn_toFavorites.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_favoritesFragment)
        }
    }

    private fun setupObservers() {
        viewModel.fetchDrinkList.observe(viewLifecycleOwner, Observer { result ->
            when(result) {
                is Resourse.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }

                is Resourse.Success -> {
                    progressBar.visibility = View.GONE
                    rv_drink.adapter = MainAdapter(requireContext(), result.data, this)
                }

                else -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error al cargar los datos", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupSerachView() {
        searchDrink.setOnQueryTextListener(object: OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setDrink(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun setupRecyclerView() {
        rv_drink.layoutManager = LinearLayoutManager(requireContext())
        rv_drink.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    override fun onDrinkClick(drink: Drink, position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("drink", drink)
        findNavController().navigate(R.id.action_mainFragment_to_cocktailDetailsFragment, bundle)
    }
}