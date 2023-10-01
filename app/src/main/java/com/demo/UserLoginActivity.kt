/*
package com.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.demo.application.MainApplication
import com.demo.common_helper.ConstantHelper.User
import com.demo.common_helper.CustomProgressDialog
import com.demo.common_helper.DefaultHelper.openActivity
import com.demo.common_helper.PreferenceHelper
import com.demo.common_helper.Validation.loginValidate
import com.demo.databinding.ActivityUserloginBinding
import com.demo.listenermodule.listnerdashboard.ListenerDashboardActivity
import javax.inject.Inject

class UserLoginActivity : AppCompatActivity() {

    @Inject
    lateinit var preferenceHelper :PreferenceHelper
    private lateinit var mBinding: ActivityUserloginBinding
    lateinit var authViewModel: AuthViewModel
    lateinit var customProgressDialog: CustomProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityUserloginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        MainApplication.instance.getComponent().inject(this)
        mBinding.SignupBtn.setOnClickListener { openActivity(UserSignupActivity::class.java) }
        mBinding.LoginBtn.setOnClickListener {


            if (loginValidate(mBinding.loginEdt.text.toString().trim()))
            {
                authViewModel.login(mBinding.loginEdt.text.toString(),preferenceHelper.getUserType())

            }
        }

        customProgressDialog = CustomProgressDialog(this)
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        observers()

    }

    fun observers(){

        authViewModel.getLoginResponse.observe(this){

            preferenceHelper.setLoggedIn(true)
            if (preferenceHelper.getUserType() != User) openActivity(
                ListenerDashboardActivity::class.java
            ) else openActivity(UserOtpActivity::class.java){
                putString("from","user_login")
                putString("otp",it.data!!.otp!!)
                putString("mobile",it.data.mobile!!)
            }

            preferenceHelper.setJwtToken(it.data!!.token!!)
            finishAffinity()
        }

        authViewModel.isLoading.observe(this) { t ->
            if (t!!) {
                customProgressDialog.show()
            } else {
                customProgressDialog.dismiss()
            }
        }
    }

}*/
