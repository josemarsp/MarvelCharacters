package br.com.josef.marvelcharacters.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.josef.marvelcharacters.model.dataclass.BaseRequest
import br.com.josef.marvelcharacters.model.dataclass.MarvelResult
import br.com.josef.marvelcharacters.repository.Repository
import br.com.josef.marvelcharacters.utils.PUBLIC_KEY
import br.com.josef.marvelcharacters.utils.md5
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val _listaComics = MutableLiveData<MutableList<MarvelResult>>()
    private val _listaPersonagens = MutableLiveData<MutableList<MarvelResult>>()
    private val _listaSeries = MutableLiveData<MutableList<MarvelResult>>()
    private val loading = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()

    var timestamp = java.lang.Long.toString(System.currentTimeMillis() / 1000)

    var hash = md5(timestamp)

    val listaComics: LiveData<MutableList<MarvelResult>>
        get() {
            return _listaComics
        }

    val listaPersonagens: LiveData<MutableList<MarvelResult>>
        get() {
        return _listaPersonagens
    }

    val listaSeries: LiveData<MutableList<MarvelResult>>
        get() {
            return _listaSeries
        }

    fun getLoading(): LiveData<Boolean> {
        return loading
    }

    val favoriteCharacters: LiveData<MutableList<MarvelResult>>
        get() = repository.favoriteCharacters

    fun saveFavorite(favorite: MarvelResult){
        viewModelScope.launch {
            repository.insertFavorite(favorite)
        }
    }


    fun allComicsCharacter(id: Int) {
        disposable.add(
            repository.getComicsCharacters(
                id,
                "comic",
                "onsaleDate",
                "50",
                timestamp,
                hash,
                PUBLIC_KEY
            )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loading.setValue(true) }
                .doOnTerminate { loading.setValue(false) }
                .subscribe(
                    { data: BaseRequest -> _listaComics.setValue(data.data.results.toMutableList()) }
                ) { throwable: Throwable -> Log.i("LOG", "allComicsCharacter" + throwable.message) }!!
        )
    }

    fun allSeriesCharacter(id: Int) {
        disposable.add(
            repository.getSeriesCharacters(
                id,
                "comic",
                "startYear",
                "50",
                timestamp,
                hash,
                PUBLIC_KEY
            )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loading.setValue(true) }
                .doOnTerminate { loading.setValue(false) }
                .subscribe(
                    { data: BaseRequest -> _listaSeries.setValue(data.data.results.toMutableList()) }
                ) { throwable: Throwable -> Log.i("LOG", "allSeriesCharacter" + throwable.message) }!!
        )
    }


    fun allCharacters( offset: Int, limit: Int) {
        disposable.add(
            repository.getCharacters("name", limit, offset, timestamp, hash, PUBLIC_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loading.setValue(true) }
                .doOnTerminate { loading.setValue(false) }
                .subscribe(
                    { data: BaseRequest -> _listaPersonagens.setValue(data.data.results.toMutableList()) }
                ) { throwable: Throwable -> Log.i("LOG", "GetAllComics" + throwable.message) }
            )
        }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}