package com.jack.web3j

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jack.web3j.databinding.ActivityMainBinding
import org.web3j.protocol.Web3j
import org.web3j.protocol.Web3jFactory
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.http.HttpService
import org.web3j.utils.Convert


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var web3j: Web3j
    // 以太坊节点
    private val prcUrl = "https://rinkeby.infura.io/v3/f2135104de0a4bd298a2cb62056c5779"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initWeb3j()
    }

    private fun initWeb3j() {
        web3j = Web3jFactory.build(HttpService(prcUrl))
        Thread {
            // 获取版本，可以验证连接是否成功
//            val version = web3j.web3ClientVersion().send().web3ClientVersion

            // 获取钱包余额
            val balance = web3j.ethGetBalance("0x50056344225622A6Ab9E0A110E7e51D577Dfc3DA", DefaultBlockParameterName.LATEST)
                .sendAsync().get().balance
            runOnUiThread {
                binding.tvVersion.text = "${Convert.fromWei(balance.toString(), Convert.Unit.ETHER)}ETH"
            }
        }.start()
    }

}