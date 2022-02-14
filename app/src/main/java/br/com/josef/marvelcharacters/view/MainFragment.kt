package br.com.josef.marvelcharacters.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import br.com.josef.marvelcharacters.R
import br.com.josef.marvelcharacters.adapter.CharacterAdapter
import br.com.josef.marvelcharacters.databinding.FragmentMainBinding
import br.com.josef.marvelcharacters.interfaces.OnClick
import br.com.josef.marvelcharacters.model.dataclass.Result
import br.com.josef.marvelcharacters.utils.isTablet
import br.com.josef.marvelcharacters.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

private lateinit var binding: FragmentMainBinding
private lateinit var adapter: CharacterAdapter
private lateinit var viewModel: MainViewModel
private var itemsSearches = 0
private val searchLimit = 20

class MainFragment : Fragment(), OnClick {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(
            inflater,
            container,
            false
        )
        viewModel = getViewModel()

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        setScrollPagingImages()

        viewModel.allCharacters(0, searchLimit)

        with(viewModel) {
            getLoading().observe(viewLifecycleOwner) { loading ->
                if (loading) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }

            listaPersonagens.observe(viewLifecycleOwner) { result ->
                adapter.updateList(result)
            }
        }

    }

    private fun setAdapter() {
        adapter = CharacterAdapter(arrayListOf(), this, requireActivity())
        binding.recyclerView.adapter = adapter
        val columns = if (isTablet(requireActivity())) 3 else 2
        binding.recyclerView.layoutManager = GridLayoutManager(requireActivity(), columns)
    }

    override fun click(result: Result?) {
        val bundle = Bundle()
        bundle.putParcelable("result", result)

        requireActivity().supportFragmentManager.beginTransaction()
            .add(
                R.id.fc_fragment,
                DetailsFragment::class.java, bundle
            )
            .addToBackStack(null)
            .commit()
    }

    private fun setScrollPagingImages() {

        binding.recyclerView.addOnScrollListener(object : OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)


                val layoutManager: GridLayoutManager =
                    recyclerView.layoutManager as GridLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisible = layoutManager.findLastVisibleItemPosition()

                val ultimoItem = lastVisible + 6 >= itemsSearches

                if (totalItemCount > 0 && ultimoItem) {
                    itemsSearches += searchLimit
                    viewModel.allCharacters(offset = itemsSearches, limit = searchLimit)
                }
            }
        })
    }

}