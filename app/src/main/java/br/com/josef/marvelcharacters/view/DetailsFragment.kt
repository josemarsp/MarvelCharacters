package br.com.josef.marvelcharacters.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.josef.marvelcharacters.R
import br.com.josef.marvelcharacters.adapter.ComicsAdapter
import br.com.josef.marvelcharacters.databinding.FragmentDetailsBinding
import br.com.josef.marvelcharacters.model.dataclass.Result
import br.com.josef.marvelcharacters.utils.ExpandedImageHelper
import br.com.josef.marvelcharacters.utils.urlForBasetImages
import br.com.josef.marvelcharacters.viewmodel.MainViewModel
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.getViewModel

private lateinit var binding: FragmentDetailsBinding
private lateinit var adapter: ComicsAdapter
private var viewModel: MainViewModel? = null

class DetailsFragment() : Fragment() {

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
        viewModel = getViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val result = requireArguments().getParcelable<Result>("result")!!
        val uri = urlForBasetImages(result)

        binding.tvTitleName.text = result.name
        if (result.description.isNotEmpty()) binding.tvComicDescription.text = result.description
        Glide.with(requireContext())
            .load(uri)
            .dontTransform()
            .placeholder(R.drawable.portrait_uncanny)
            .into(binding.ivCharacterImage)

        binding.ivCharacterImage.setOnClickListener {
            ExpandedImageHelper.uriImage(uri,requireContext())
        }

        viewModel?.allComicsCharacter(result.id)
        viewModel?.getListaComics()?.observe(this) { results ->
            binding.tvComicsTitle.visibility = View.VISIBLE
            binding.rvCharacterComics.visibility = View.VISIBLE
            adapter = ComicsAdapter(results, requireActivity())
            binding.rvCharacterComics.adapter = adapter
            binding.rvCharacterComics.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        }
    }


//    companion object {
//        fun newInstance(bundle: Bundle?) = DetailsFragment().apply {
//            arguments = Bundle().apply {
//            }
//        }
//    }
}