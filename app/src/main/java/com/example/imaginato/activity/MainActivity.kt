package com.example.imaginato.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import com.example.imaginato.R
import com.example.imaginato.databinding.ActivityLoginBinding
import com.example.imaginato.databinding.ActivityMainBinding
import com.example.imaginato.roomDb.AppDataBase
import java.util.Observer

class MainActivity : AppCompatActivity() {
    private lateinit var appDataBase: AppDataBase

    private lateinit var binding : ActivityMainBinding
    var username : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDataBase = AppDataBase.getInstance(this)
        getData()

    }



    //get and set roomdb data
    private fun getData() {
        val userid = intent.getStringExtra("username")

        userid?.let {
            appDataBase.databaseDao.getUserName(userid).observe(this, androidx.lifecycle.Observer {
                username = it.email
                binding.tvUsername.setText("Hello"+" "+ username)
            })
        }



    }
}