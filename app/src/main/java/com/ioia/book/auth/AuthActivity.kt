package com.ioia.book.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ioia.book.R
import com.ioia.book.auth.login.LoginActivity
import com.ioia.book.dashboard.DashboardActivity
import com.ioia.book.utils.support.SessionManager
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.auth_act.*

class AuthActivity : AppCompatActivity() {

    private val viewmodel by viewModels<AuthViewModel> {
        Injector.provideAuthViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.auth_act)

        initAuth()
        initListener()
    }

    private fun initAuth(){
        val session = SessionManager(this)
        val phone = session.getPhone()

        if(phone != null && phone != ""){
            DashboardActivity.startActivity(this)
            finish()
        }else{
            initCaouselView()
        }
    }

    private fun initListener(){
        btn_start.setOnClickListener {
            LoginActivity.startActivity(this)
            finish()
        }
    }

    private fun initCaouselView(){
        val images = arrayListOf(
            R.drawable.satu,
            R.drawable.two,
            R.drawable.tiga,
            R.drawable.empat
        )

        val imageListener = ImageListener { position, imageView ->
            imageView.setImageResource(images[position])
        }

        carouselview.pageCount = images.size
        carouselview.setImageListener(imageListener)
    }

    companion object{
        fun startActivity(context: Context){
            context.startActivity(Intent(context, AuthActivity::class.java))
        }
    }
}
