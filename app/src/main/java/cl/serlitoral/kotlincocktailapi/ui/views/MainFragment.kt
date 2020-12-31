package cl.serlitoral.kotlincocktailapi.ui.views

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
import cl.serlitoral.kotlincocktailapi.data.DataSourceImpl
import cl.serlitoral.kotlincocktailapi.data.model.Drink
import cl.serlitoral.kotlincocktailapi.databinding.ActivityMainBinding
import cl.serlitoral.kotlincocktailapi.databinding.FragmentMainBinding
import cl.serlitoral.kotlincocktailapi.domain.RepoImpl
import cl.serlitoral.kotlincocktailapi.ui.MainAdapter
import cl.serlitoral.kotlincocktailapi.ui.viewmodel.MainViewModel
import cl.serlitoral.kotlincocktailapi.ui.viewmodel.VMFactory
import cl.serlitoral.kotlincocktailapi.vo.Resourse
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), MainAdapter.OnDrinkClickListener {


    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    //Se inyecta dependencia al Model
    private val viewModel by viewModels<MainViewModel> {
        VMFactory(RepoImpl(
            DataSourceImpl(AppDatabase.getDatabase(requireActivity().applicationContext))
        )
    )}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSearchView()
        setupObservers()

        binding.btnToFavorites.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_favoritesFragment)
        }
    }

    private fun setupObservers() {
        viewModel.fetchDrinkList.observe(viewLifecycleOwner, Observer { result ->
            when(result) {
                is Resourse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Resourse.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvDrink.adapter = MainAdapter(requireContext(), result.data, this)
                }

                else -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error al cargar los datos", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupSearchView() {
        binding.searchDrink.setOnQueryTextListener(object: OnQueryTextListener {
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
        binding.rvDrink.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDrink.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    override fun onDrinkClick(drink: Drink) {
        val bundle = Bundle()
        bundle.putParcelable("drink", drink)
        findNavController().navigate(R.id.action_mainFragment_to_drinkDetailsFragment, bundle)
    }
}