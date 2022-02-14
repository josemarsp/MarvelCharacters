package br.com.josef.marvelcharacters.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.josef.marvelcharacters.R
import br.com.josef.marvelcharacters.databinding.ActivityMainBinding
import org.koin.androidx.fragment.android.setupKoinFragmentFactory

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
//    private lateinit var item: Menu

    override
    fun onCreate(savedInstanceState: Bundle?) {
        setupKoinFragmentFactory()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.tbMarvel)

        this.supportFragmentManager.beginTransaction()
            .replace(
                R.id.fc_fragment,
                MainFragment::class.java, null
            )
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.action_favorite -> {
                this.supportFragmentManager.beginTransaction()
                    .add(
                        R.id.fc_fragment,
                        FavoriteFragment::class.java, null
                    )
                    .addToBackStack(null)
                    .commit()
                true
            }

            R.id.action_search -> {
                true
            }

            else -> {
                super.onOptionsItemSelected(item)

            }
        }

    fun renameMenu(s: String) {
        supportActionBar?.title = s
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size == 1) {
            finish()
        } else {
            supportFragmentManager.popBackStack()
        }
    }


}