package com.ioia.book.utang.add.hutang

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.ioia.book.R
import com.ioia.book.databinding.HutangActBinding
import com.ioia.book.utang.Injector
import com.ioia.book.utils.StringHelper
import kotlinx.android.synthetic.main.hutang_act.*

class HutangActivity : AppCompatActivity() {

    private val viewmodel by viewModels<HutangViewModel> {
        Injector.providerAddHutangViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        initToolbar()
        initViewModel()

        viewmodel.getCustomerById(customerId)
    }

    private fun initDataBinding(){
        val binding = DataBindingUtil.setContentView<HutangActBinding>(this, R.layout.hutang_act)
        binding.lifecycleOwner = this
        binding.viewmodel = viewmodel
    }

    private fun initToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun initViewModel(){
        viewmodel.customer.observe(this, Observer {
            supportActionBar?.title = it.name
            supportActionBar?.subtitle = it.phone
        })
    }

    companion object{
        var customerId = 0L
        var businessId = 0L

        fun startActivity(context: Context, businessId: Long, customerId: Long){
            context.startActivity(Intent(context, HutangActivity::class.java))
            this.customerId = customerId
            this.businessId = businessId
        }
    }
}
