package com.bitcoin.viewer.remoteDataSource

import com.bitcoin.viewer.model.BitcoinResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface RemoteDataSource {

    @GET("/v2/assets")
    fun getData(
        @Query("ids", encoded = true) ids : String = "bitcoin"
    ) : Observable<BitcoinResponse>
}