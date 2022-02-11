package br.com.josef.marvelcharacters.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.josef.marvelcharacters.adapter.RecyclerViewAdapter
import br.com.josef.marvelcharacters.databinding.ActivityMainBinding
import br.com.josef.marvelcharacters.interfaces.OnClick
import br.com.josef.marvelcharacters.model.dataclass.Result
import br.com.josef.marvelcharacters.viewmodel.ComicsViewModel

class MainActivity : AppCompatActivity(), OnClick {

    private var recyclerView: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var adapter: RecyclerViewAdapter? = null
    private val listaComics: MutableList<Result> = ArrayList()
    private var listaPersonagens: List<Result> = ArrayList()
    private val viewModel: ComicsViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.allComics
        viewModel.allCharacters

//        adapter = RecyclerViewAdapter(listaComics, this, this)
//        binding.recyclerView.adapter  = adapter
//        binding.recyclerView.layoutManager = GridLayoutManager(this, 3)

        viewModel.getLoading().observe(this) { loading ->
            if (loading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        viewModel.getListaComics().observe(this) { results ->
            adapter = RecyclerViewAdapter(results, this, this)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = GridLayoutManager(this, 3)
        }

        viewModel.getListaPersonagens().observe(this) {
                results -> listaPersonagens = results }
    }

    override fun click(result: Result?) {
//        fun click(result: Result?) {
//            val intent = Intent(this@MainActivity, DetalheActivity::class.java)
        val bundle = Bundle()
//            bundle.putParcelable("Result", result)
//            intent.putExtras(bundle)
//            startActivity(intent)
//        }
    }
}