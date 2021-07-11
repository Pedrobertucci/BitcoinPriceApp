package com.bitcoin.viewer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bitcoin.viewer.databinding.ActivityMainBinding
import com.bitcoin.viewer.viewModel.BitcoinViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var viewModel: BitcoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObservers()
        viewModel.getData()
    }

    private fun setupObservers() {
        viewModel.bitcoinLiveData.observe(this, {
            Log.d("mainActivity", "bitcoinLiveData: $it")

            it?.let {
                viewModel.updateData()
                binding.txtPriceValue.text = it.coinData.first().priceUsd
            }
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