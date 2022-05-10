package com.jack.web3j

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jack.web3j.databinding.ActivityMainBinding
import org.web3j.crypto.WalletUtils
import org.web3j.protocol.Web3jFactory
import org.web3j.protocol.http.HttpService

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initWeb3j()
    }

    private fun initWeb3j() {
        val web3j = Web3jFactory.build(HttpService("https://http-mainnet.hecochain.com"))
        Thread {
            val version = web3j.web3ClientVersion().send().web3ClientVersion
            runOnUiThread {
                binding.tvVersion.postDelayed({
                    binding.tvVersion.text = version
                }, 300)
            }
        }.start()
    }
}