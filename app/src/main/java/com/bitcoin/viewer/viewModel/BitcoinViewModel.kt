package com.bitcoin.viewer.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitcoin.viewer.model.BitcoinResponse
import com.bitcoin.viewer.remoteDataSource.RemoteDataSource
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class BitcoinViewModel @Inject constructor(private val remoteDataSource: RemoteDataSource) : ViewModel() {
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val bitcoinMutableData = MutableLiveData<BitcoinResponse>()
    val bitcoinLiveData: LiveData<BitcoinResponse> get() = bitcoinMutableData

    private val errorMutableData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = errorMutableData

    @SuppressLint("CheckResult")
    fun updateData() {
        Completable.timer(5, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .subscribe(this::getData)
    }

    fun getData() {
        compositeDisposable.add(remoteDataSource.getData()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                it?.let {
                    bitcoinMutableData.value = it
                }
            }, {
                it?.let {
                    errorMutableData.value = it.message
                }
            }))
    }

    fun clear() {
        compositeDisposable.clear()
    }
}