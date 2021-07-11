package com.bitcoin.viewer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bitcoin.viewer.R
import com.bitcoin.viewer.viewModel.BitcoinViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: BitcoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupObservers()
        viewModel.getData()
    }

    private fun setupObservers() {
        viewModel.bitcoinLiveData.observe(this, {
            Log.d("mainActivity", "bitcoinLiveData: $it")
        })

        viewModel.errorLiveData.observe(this, {
            Log.d("mainActivity", "errorLiveData: $it")
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clear()
    }
}