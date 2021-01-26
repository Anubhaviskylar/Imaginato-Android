package com.example.imaginato.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.imaginato.R
import com.example.imaginato.databinding.ActivityLoginBinding
import com.example.imaginato.network.AuthApi
import com.example.imaginato.network.Resource
import com.example.imaginato.network.RetrofitClient
import com.example.imaginato.repository.AuthRepository
import com.example.imaginato.roomDb.AppDataBase
import com.example.imaginato.roomDb.Email
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var  appDataBase: AppDataBase
    private lateinit var binding : ActivityLoginBinding
    private lateinit var viewmodel : LogInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        appDataBase = AppDataBase.getInstance(this)

        val api = RetrofitClient().buildApi(AuthApi::class.java)
        val factory = ViewModelFactory(AuthRepository(api))
        viewmodel= ViewModelProvider(this, factory).get(LogInViewModel::class.java)


        binding.btnLogin.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            if (isValid())
            {

                val email = binding.etEmailAddress.text.toString().trim()
                val password = binding.etPassword.text.toString().trim()

                /*Api Calling*/
                binding.progressBar.isShown
                viewmodel.login(email, password)

            }

        }

        viewmodel.loginResponse.observe(this, Observer {
            when (it) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    //Save Email Into RoomDb
                    val user = Email(it.value.user.userId,it.value.user.userName)
                    CoroutineScope(Dispatchers.IO).launch {
                        appDataBase.databaseDao.insertEmail(user)
                    }

                   // Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,MainActivity::class.java)
                    intent.putExtra("username",it.value.user.userId)
                    startActivity(intent)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this,"Login Failure", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    // Validation of All fields

    private fun isValid(): Boolean {
        var isValid = true
        if (binding.etEmailAddress.text!!.trim().toString().isEmpty()) {
            isValid = false
            binding.etEmailAddress.error = "Username can't be empty"

        } else if (binding.etPassword.text!!.trim().toString().isEmpty()) {
            isValid = false
            binding.etEmailAddress.error = "Password can't be empty"
        }
        return isValid

    }
}