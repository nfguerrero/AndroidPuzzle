package com.example.trialgame

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log.d


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

       nextpage.setOnClickListener {
           d("dalton", "button pressed")
           //d("dalton", "${editText.text}"
           //message.text = "${editText.text}"
           startActivity(Intent(this, AboutMe::class.java))
       }

    }



}
