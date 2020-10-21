package com.ioia.book.utang.add

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ioia.book.R
import com.ioia.book.customer.CustomerRVAdapter
import com.ioia.book.databinding.AddActBinding
import com.ioia.book.utang.Injector
import kotlinx.android.synthetic.main.add_act.*

class AddActivity : AppCompatActivity() {

    private val viewmodel by viewModels<AddViewModel> {
        Injector.providerAddViewModelFactory(this, businessId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initViewModel()
        initListener()
        initCustomers()
    }

    override fun onResume() {
        super.onResume()
        viewmodel.getAllCustomer()
    }

    private fun initDataBinding(){
        val binding = DataBindingUtil.setContentView<AddActBinding>(this, R.layout.add_act)
        binding.lifecycleOwner = this
        binding.viewmodel = viewmodel
    }

    private fun initViewModel(){
        viewmodel.customers.observe(this, Observer {
            rv_contact.adapter?.notifyDataSetChanged()
        })
    }

    private fun initListener(){
        tv_on_back.setOnClickListener {
            onBackPressed()
        }

        til_search_customer.editText?.doOnTextChanged { text, _, _, _ ->
            viewmodel.getAllCustomerBySearch(text.toString())
        }
    }

    private fun initCustomers(){
        val adp = CustomerRVAdapter(viewmodel.customers, businessId)

        rv_contact.apply {
            adapter = adp
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(this@AddActivity)
            addItemDecoration(DividerItemDecoration(this@AddActivity, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
        }
    }

    companion object{
        var businessId: Long = 0

        fun startActivity(context: Context, businessId: Long){
            context.startActivity(Intent(context, AddActivity::class.java))
            this.businessId = businessId
        }
    }
}
