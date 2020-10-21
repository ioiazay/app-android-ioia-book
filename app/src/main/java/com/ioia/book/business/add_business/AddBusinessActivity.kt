package com.ioia.book.business.add_business

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ioia.book.R
import com.ioia.book.business.Injector
import com.ioia.book.databinding.AddBusinessActBinding
import com.ioia.book.utils.support.SessionManager
import kotlinx.android.synthetic.main.add_business_act.*

class AddBusinessActivity : AppCompatActivity() {

    private val viewmodel by viewModels<AddBusinessViewModel> {
        Injector.provideAddBusinessViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        initToolbar()
        initRecyclerView()
    }

    private fun initDataBinding(){
        val binding = DataBindingUtil.setContentView<AddBusinessActBinding>(this, R.layout.add_business_act)
        binding.lifecycleOwner = this
        binding.viewmodel = viewmodel
    }

    private fun initToolbar(){
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun initRecyclerView(){
        val type = listOf(
            "Mini Market / Kelontong / Retail",
            "Restoran / Cafe / Tempat Makan",
            "Butik / Pakaian / Aksesoris dan Penampilan",
            "Salon dan Barbershop"
        )

        val businessTypeAdapter = BusinessTypeRVAdapter(type){
            if(it != null && it != ""){
                viewmodel.type.value = it
                viewmodel.isBusinessTypeOpen.value = false
            }
        }

        rv_business_type.apply {
            adapter = businessTypeAdapter
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(this@AddBusinessActivity)
            addItemDecoration(DividerItemDecoration(this@AddBusinessActivity, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
        }
    }

    companion object{
        fun startActivity(context: Context){
            context.startActivity(Intent(context, AddBusinessActivity::class.java))
        }
    }
}
