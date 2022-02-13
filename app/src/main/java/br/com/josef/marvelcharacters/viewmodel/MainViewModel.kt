package br.com.josef.marvelcharacters.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.josef.marvelcharacters.model.dataclass.ComicsResponse
import br.com.josef.marvelcharacters.model.dataclass.Result
import br.com.josef.marvelcharacters.repository.ComicsRepository
import br.com.josef.marvelcharacters.utils.PUBLIC_KEY
import br.com.josef.marvelcharacters.utils.md5
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel() : ViewModel() {
    private val repository = ComicsRepository()
    private val listaComics = MutableLiveData<MutableList<Result>>()
    private val listaPersonagens = MutableLiveData<MutableList<Result>>()
    private val loading = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()

    var timestamp = java.lang.Long.toString(System.currentTimeMillis() / 1000)

    var hash = md5(timestamp)
    fun getListaComics(): LiveData<MutableList<Result>> {
        return listaComics
    }

    fun getListaPersonagens(): LiveData<MutableList<Result>> {
        return listaPersonagens
    }

    fun getLoading(): LiveData<Boolean> {
        return loading
    }

    val allComics: Unit
        get() {
            disposable.add(
                repository.getComics("magazine", "comics", true, "focDate", timestamp, hash, PUBLIC_KEY)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { loading.setValue(true) }
                    .doOnTerminate { loading.setValue(false) }
                    .subscribe(
                        { data: ComicsResponse -> listaComics.setValue(data.data.results.toMutableList()) }
                    ) { throwable: Throwable -> Log.i("LOG", "GetAllComics" + throwable.message) }!!
            )
        }


    val allCharacters: Unit
        get() {

            disposable.add(
                repository.getCharacters("name", timestamp, hash, PUBLIC_KEY)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { loading.setValue(true) }
                    .doOnTerminate { loading.setValue(false) }
                    .subscribe(
                        { data: ComicsResponse -> listaPersonagens.setValue(data.data.results.toMutableList()) }
                    ) { throwable: Throwable -> Log.i("LOG", "GetAllComics" + throwable.message) }
            )
        }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}