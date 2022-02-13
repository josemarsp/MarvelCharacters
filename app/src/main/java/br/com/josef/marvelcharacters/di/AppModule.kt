package br.com.josef.marvelcharacters.di

import br.com.josef.marvelcharacters.view.DetailsFragment
import br.com.josef.marvelcharacters.viewmodel.MainViewModel
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
//    viewModel { MainViewModel() }
    fragment { DetailsFragment() }
//    scope<MainActivity> {
//        fragment { DetailsFragment() }
//}
}

val mainModule = module {
    viewModel { MainViewModel() }
}




