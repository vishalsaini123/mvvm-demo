package com.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.demo.application.MainApplication
import com.demo.common_helper.ConstantHelper.Listener
import com.demo.common_helper.ConstantHelper.User
import com.demo.common_helper.DefaultHelper.openActivity
import com.demo.common_helper.PreferenceHelper
import com.demo.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var preferenceHelper: PreferenceHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MainApplication.instance.getComponent().inject(this)
        binding.userBtn.setOnClickListener {
          //  openActivity(UserLoginActivity::class.java)
            preferenceHelper.setUserType(User)

        }
        binding.listenerBtn.setOnClickListener {
          //  openActivity(UserLoginActivity::class.java)
            preferenceHelper.setUserType(Listener)


        }



    }
}