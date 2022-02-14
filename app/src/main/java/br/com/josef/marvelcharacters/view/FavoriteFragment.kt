package br.com.josef.marvelcharacters.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import br.com.josef.marvelcharacters.adapter.CharacterAdapter
import br.com.josef.marvelcharacters.databinding.FragmentFavoritesBinding
import br.com.josef.marvelcharacters.databinding.FragmentMainBinding
import br.com.josef.marvelcharacters.interfaces.OnClick
import br.com.josef.marvelcharacters.model.dataclass.MarvelResult
import br.com.josef.marvelcharacters.utils.ExpandedImageHelper
import br.com.josef.marvelcharacters.utils.isTablet
import br.com.josef.marvelcharacters.utils.urlForBasetImages
import br.com.josef.marvelcharacters.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoriteFragment : Fragment(), OnClick {
    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var adapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        with(viewModel) {
            favoriteCharacters.observe(viewLifecycleOwner) { result ->
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

    override fun click(marvelResult: MarvelResult?) {
        ExpandedImageHelper.uriImage(urlForBasetImages(marvelResult!!),requireContext())
    }
}