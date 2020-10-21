package com.ioia.book.utang.add.contact

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.ioia.book.R
import com.ioia.book.databinding.ContactActBinding
import com.ioia.book.utang.Injector
import kotlinx.android.synthetic.main.contact_act.*

class ContactActivity : AppCompatActivity() {
    private val viewmodel by viewModels<ContactViewModel> {
        Injector.providerContactViewModelFactory(this, businessId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        initToolbar()
    }

    private fun initDataBinding(){
        val binding = DataBindingUtil.setContentView<ContactActBinding>(this, R.layout.contact_act)
        binding.lifecycleOwner = this
        binding.viewmodel = viewmodel
    }

    private fun initToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Tambah pelanggan baru"
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    companion object{
        var businessId: Long = 0
        var name = ""
        var phone = ""

        fun startActivity(context: Context, businessId: Long, name: String, phone: String){
            context.startActivity(Intent(context, ContactActivity::class.java))

            this.businessId = businessId
            this.name = name
            this.phone = phone
        }
    }
}
