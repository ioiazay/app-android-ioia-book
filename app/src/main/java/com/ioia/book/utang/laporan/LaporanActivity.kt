package com.ioia.book.utang.laporan

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.ioia.book.R
import com.ioia.book.databinding.LaporanActBinding
import com.ioia.book.utang.Injector
import com.ioia.book.utils.DateHelper
import kotlinx.android.synthetic.main.laporan_act.*

class LaporanActivity : AppCompatActivity() {

    private val viewmodel by viewModels<LaporanViewModel> {
        Injector.providerLaporanViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initToolbar()
        initViewModel()
        initTableView()
    }

    override fun onResume() {
        super.onResume()
        viewmodel.getHutangListByBusiness(businessId)
    }

    private fun initDataBinding(){
        val binding = DataBindingUtil.setContentView<LaporanActBinding>(this, R.layout.laporan_act)
        binding.lifecycleOwner = this
        binding.viewmodel = viewmodel
    }

    private fun initToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Laporan Utang Piutang"
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun initViewModel(){
        viewmodel.hutangListByBusiness.observe(this, Observer {

        })
    }

    private fun initTableView(){
        // TODO
    }

    companion object{
        var businessId: Long = 0

        fun startActivity(context: Context, businessId: Long){
            context.startActivity(Intent(context, LaporanActivity::class.java))
            this.businessId = businessId
        }
    }
}
