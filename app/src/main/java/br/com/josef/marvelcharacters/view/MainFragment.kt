package br.com.josef.marvelcharacters.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
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


class MainFragment() : Fragment(), OnClick {
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

        viewModel.allCharacters

        viewModel.getLoading().observe(this) { loading ->
            if (loading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        viewModel.listaPersonagens.observe(this) {
            adapter = CharacterAdapter(it, this, requireActivity())
            binding.recyclerView.adapter = adapter
            val columns = if (isTablet(requireActivity())) 3 else 2
            binding.recyclerView.layoutManager = GridLayoutManager(requireActivity(), columns)
        }
    }

    override fun click(result: Result?) {
        val bundle = Bundle()
        bundle.putParcelable("result", result)

        requireActivity().supportFragmentManager.beginTransaction()
            .add(
                R.id.fc_fragment,
                DetailsFragment::class.java, bundle)
            .addToBackStack(null)
            .commit()

//        val transaction = activity?.supportFragmentManager?.beginTransaction()
//        transaction?.add(
//            R.id.fl_forgot,
//            RecoveryPasswordCodeFragment.newInstance(bundle)
//        )
//        transaction?.addToBackStack(null)
//        transaction?.commit()
    }
}