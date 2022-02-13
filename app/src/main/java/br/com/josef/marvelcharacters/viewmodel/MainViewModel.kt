package br.com.josef.marvelcharacters.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.josef.marvelcharacters.model.dataclass.BaseRequest
import br.com.josef.marvelcharacters.model.dataclass.Result
import br.com.josef.marvelcharacters.repository.Repository
import br.com.josef.marvelcharacters.utils.PUBLIC_KEY
import br.com.josef.marvelcharacters.utils.md5
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {
    private val repository = Repository()
    private val _listaComics = MutableLiveData<MutableList<Result>>()
    private val _listaPersonagens = MutableLiveData<MutableList<Result>>()
    private val _listaSeries = MutableLiveData<MutableList<Result>>()
    private val loading = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()

    var timestamp = java.lang.Long.toString(System.currentTimeMillis() / 1000)

    var hash = md5(timestamp)

    val listaComics: LiveData<MutableList<Result>>
        get() {
            return _listaComics
        }

    val listaPersonagens: LiveData<MutableList<Result>>
        get() {
        return _listaPersonagens
    }

    val listaSeries: LiveData<MutableList<Result>>
        get() {
            return _listaSeries
        }

    fun getLoading(): LiveData<Boolean> {
        return loading
    }

//    val allComics: Unit
//        get() {
//            disposable.add(
//                repository.getComics("magazine", "comics", true, "focDate", timestamp, hash, PUBLIC_KEY)
//                    .subscribeOn(Schedulers.newThread())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .doOnSubscribe { loading.setValue(true) }
//                    .doOnTerminate { loading.setValue(false) }
//                    .subscribe(
//                        { data: BaseRequest -> listaComics.setValue(data.data.results.toMutableList()) }
//                    ) { throwable: Throwable -> Log.i("LOG", "GetAllComics" + throwable.message) }!!
//            )
//        }

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


    val allCharacters: Unit
        get() {

            disposable.add(
                repository.getCharacters("name", timestamp, hash, PUBLIC_KEY)
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