package br.com.josef.marvelcharacters.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.josef.marvelcharacters.R
import br.com.josef.marvelcharacters.databinding.ActivityMainBinding
import org.koin.androidx.fragment.android.setupKoinFragmentFactory

class MainActivity : AppCompatActivity(){

//    private var characterAdapter: CharacterAdapter? = null
//    private val listaComics: MutableList<Result> = ArrayList()

    //    private var listaPersonagens: List<Result> = ArrayList()

//    private val viewModel: MainViewModel by viewModel()


    private lateinit var binding: ActivityMainBinding

    override
    fun onCreate(savedInstanceState: Bundle?) {
        setupKoinFragmentFactory()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        this.supportFragmentManager.beginTransaction()
            .replace(
                R.id.fc_fragment,
                MainFragment::class.java, null)
            .commit()
    }



    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size == 1) {
            finish()
        } else {
            supportFragmentManager.popBackStack()
        }
    }


    // viewModel.allComics
//        viewModel.allCharacters

//        adapter = RecyclerViewAdapter(listaComics, this, this)
//        binding.recyclerView.adapter  = adapter
//        binding.recyclerView.layoutManager = GridLayoutManager(this, 3)



//        viewModel.getListaComics().observe(this) { results ->
//            comicsAdapter = ComicsAdapter(results, this, this)
//            binding.recyclerView.adapter = comicsAdapter
//            binding.recyclerView.layoutManager = GridLayoutManager(this, 3)
//        }
}