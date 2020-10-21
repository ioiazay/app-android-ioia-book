package com.ioia.book.auth.login

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.ioia.book.R
import com.ioia.book.auth.Injector
import com.ioia.book.databinding.LoginActBinding
import kotlinx.android.synthetic.main.login_act.*

class LoginActivity : AppCompatActivity() {

    private val viewmodel by viewModels<LoginViewModel> {
        Injector.provideLoginViewModelFactory(this)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        initListener()
        initViewModel()
    }

    private fun initDataBinding(){
        val binding = DataBindingUtil.setContentView<LoginActBinding>(this, R.layout.login_act)
        binding.lifecycleOwner = this
        binding.viewmodel = viewmodel
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initViewModel(){
        viewmodel.isOnSaveData.observe(this, Observer {
            if(it){
                btn_wa.isClickable = false
                btn_wa.background.setTintList(ContextCompat.getColorStateList(this, R.color.global_grey))

                btn_sms.isClickable = false
                btn_sms.background.setTintList(ContextCompat.getColorStateList(this, R.color.global_grey))
            }
        })
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initListener(){
        input_layout.editText?.doOnTextChanged { text, _, _, _ ->

            if(text?.length!! >= 9){
                btn_wa.isClickable = true
                btn_wa.background.setTintList(ContextCompat.getColorStateList(this, R.color.dark_sky_blue))

                btn_sms.isClickable = true
                btn_sms.background.setTintList(ContextCompat.getColorStateList(this, R.color.dark_sky_blue))
            }else{
                btn_wa.isClickable = false
                btn_wa.background.setTintList(ContextCompat.getColorStateList(this, R.color.global_grey))

                btn_sms.isClickable = false
                btn_sms.background.setTintList(ContextCompat.getColorStateList(this, R.color.global_grey))
            }
        }
    }

    companion object{
        fun startActivity(context: Context){
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }
}
