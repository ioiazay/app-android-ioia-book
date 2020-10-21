package com.ioia.book.utang.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ioia.book.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_act)
    }

    companion object{
        var hutangId: Long = 0

        fun startActivity(context: Context, hutangId: Long){
            context.startActivity(Intent(context, DetailActivity::class.java))
            this.hutangId = hutangId
        }
    }
}
