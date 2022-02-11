package br.com.josef.marvelcharacters.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.josef.marvelcharacters.model.dataclass.ComicsResponse
import br.com.josef.marvelcharacters.model.dataclass.Result
import br.com.josef.marvelcharacters.repository.ComicsRepository
import br.com.josef.marvelcharacters.utils.md5
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ComicsViewModel() : ViewModel() {
    private val repository = ComicsRepository()
    private val listaComics = MutableLiveData<MutableList<Result>>()
    private val listaPersonagens = MutableLiveData<List<Result>>()
    private val loading = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()

    // Timestamp do horário da requisição
    var ts = java.lang.Long.toString(System.currentTimeMillis() / 1000)

    // Hash cria com as chaves pública e privada e o timestamp
    var hash = md5(ts + PRIVATE_KEY + PUBLIC_KEY)
    fun getListaComics(): LiveData<MutableList<Result>> {
        return listaComics
    }

    fun getListaPersonagens(): LiveData<List<Result>> {
        return listaPersonagens
    }

    fun getLoading(): LiveData<Boolean> {
        return loading
    }

    val allComics: Unit
        get() {
            disposable.add(
                repository.getComics("magazine", "comics", true, "focDate", ts, hash, PUBLIC_KEY)
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
                repository.getCharacters("name", ts, hash, PUBLIC_KEY)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { loading.setValue(true) }
                    .doOnTerminate { loading.setValue(false) }
                    .subscribe(
                        { data: ComicsResponse -> listaPersonagens.setValue(data.data.results) }
                    ) { throwable: Throwable -> Log.i("LOG", "GetAllComics" + throwable.message) }
            )
        }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    companion object {
        // Parâmetros necessários para requisição da API MARVEL
        // Chave pública que será usada como como parâmetro
        const val PUBLIC_KEY = "b975c2aca612a721ed13cf5ba7345f3c"

        // Chave privada que será usada como como parâmetro
        const val PRIVATE_KEY = "4c616f6d7f4ea2cc73ea0ee137a3a4254c0f1324"
    }
}