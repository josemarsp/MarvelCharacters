package br.com.josef.marvelcharacters.view

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.josef.marvelcharacters.R
import br.com.josef.marvelcharacters.adapter.ComicsAdapter
import br.com.josef.marvelcharacters.adapter.SeriesAdapter
import br.com.josef.marvelcharacters.databinding.FragmentDetailsBinding
import br.com.josef.marvelcharacters.model.dataclass.MarvelResult
import br.com.josef.marvelcharacters.utils.ExpandedImageHelper
import br.com.josef.marvelcharacters.utils.urlForBasetImages
import br.com.josef.marvelcharacters.view.MainFragment.Companion.KEY
import br.com.josef.marvelcharacters.viewmodel.MainViewModel
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {
    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var comicsAdapter: ComicsAdapter
    private lateinit var seriesAdapter: SeriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val result = requireArguments().getParcelable<MarvelResult>(KEY)!!
        val uri = urlForBasetImages(result)
        initViews(uri, result)
        viewModel.allSeriesCharacter(result.id)
        viewModel.allComicsCharacter(result.id)

        binding.ivStar.setOnClickListener {
            viewModel.saveFavorite(result)
            binding.ivStar.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_star_48))
        }

        binding.ivCharacterImage.setOnClickListener {
            ExpandedImageHelper.uriImage(uri, requireContext())
        }

        with(viewModel) {
            listaComics.observe(viewLifecycleOwner) { results ->
                if (results.count() > 0) {
                    binding.tvComicsTitle.visibility = View.VISIBLE
                    binding.rvCharacterComics.visibility = View.VISIBLE
                    comicsAdapter = ComicsAdapter(results, requireContext())
                    binding.rvCharacterComics.adapter = comicsAdapter
                    binding.rvCharacterComics.layoutManager =
                        LinearLayoutManager(
                            requireActivity(),
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                }
            }
            listaSeries.observe(viewLifecycleOwner) { results ->
                if (results.count() > 0){
                    binding.tvSeriesTitle.visibility = View.VISIBLE
                    binding.rvCharacterSeries.visibility = View.VISIBLE
                    seriesAdapter = SeriesAdapter(results, requireContext())
                    binding.rvCharacterSeries.adapter = seriesAdapter
                    binding.rvCharacterSeries.layoutManager =
                        LinearLayoutManager(
                            requireActivity(),
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                }
            }
        }
    }

    private fun initViews(uri: Uri, result: MarvelResult) {
        if (!result.description.isNullOrEmpty()) binding.tvComicDescription.text =
            result.description

        (activity as MainActivity?)?.renameMenu(result.name!!) //Name on Top

        Glide.with(requireContext())
            .load(uri)
            .dontTransform()
            .placeholder(R.drawable.portrait_uncanny)
            .into(binding.ivCharacterImage)


    }

    override fun onDetach() {
        super.onDetach()
        (activity as MainActivity?)?.renameMenu("MarvelApp")
    }
}