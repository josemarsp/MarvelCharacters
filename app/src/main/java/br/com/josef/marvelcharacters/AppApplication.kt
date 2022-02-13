package br.com.josef.marvelcharacters

import android.app.Application
import br.com.josef.marvelcharacters.di.appModule
import br.com.josef.marvelcharacters.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            fragmentFactory()
            modules(appModule, mainModule)
        }
    }
}