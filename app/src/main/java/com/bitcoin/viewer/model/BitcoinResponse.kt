package com.bitcoin.viewer.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BitcoinResponse (
    @SerializedName("data")
    val coinData: ArrayList<CoinData>?,
    val timestamp: Long?
) : Parcelable

@Parcelize
data class CoinData (
    val id: String?,
    val rank: String?,
    val symbol: String?,
    val name: String?,
    val supply: String?,
    val maxSupply: String?,
    val marketCapUsd: String?,
    val volumeUsd24Hr: String?,
    val priceUsd: String?,
    val changePercent24Hr: String?,
    val vwap24Hr: String?,
    val explorer: String?
) : Parcelable
