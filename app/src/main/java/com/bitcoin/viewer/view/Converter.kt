package com.bitcoin.viewer.view

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.DecimalFormat
import java.math.RoundingMode

object Converter {

    @SuppressLint("SetTextI18n")
    @BindingAdapter("currency")
    @JvmStatic
    fun bindCurrency(view: TextView, amount: String?) {
        amount?.let {
            view.text = "$ ${amount.toDouble().convertMoney()}"
        } ?: run {
            view.text = "Unknown"
        }
    }

    @SuppressLint("SetTextI18n")
    @BindingAdapter("percentage")
    @JvmStatic
    fun bindPercentage(view: TextView, percentage: String?) {
        percentage?.let {
            view.text = "${percentage.toDouble().convertPercentage()}"
        } ?: run {
            view.text = "Unknown"
        }
    }

    fun Double.convertMoney(): String {
        val format = DecimalFormat("#,###.00")
        format.isDecimalSeparatorAlwaysShown = false
        return format.format(this).toString()
    }

    fun Double.convertPercentage(): String {
        val df = DecimalFormat("#,##0.0%")
        df.roundingMode = RoundingMode.HALF_UP
        var formattedValue: String = df.format(this)
        formattedValue = formattedValue.replace("^-(?=0(\\.0*)?$)".toRegex(), "")
        return formattedValue
    }
}