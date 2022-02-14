package br.com.josef.marvelcharacters.di

import br.com.josef.marvelcharacters.model.database.MarvelDatabase
import br.com.josef.marvelcharacters.repository.Repository
import br.com.josef.marvelcharacters.view.DetailsFragment
import br.com.josef.marvelcharacters.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    fragment { DetailsFragment() }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val repository = module {
    single { Repository(get()) }
}

val daoModule = module {
    single { MarvelDatabase.getInstance(androidContext()).marvelResultDao }

}
