package com.demo.repository

import android.content.Context
import com.demo.application.MainApplication
import com.demo.retrofit.APIService
import javax.inject.Inject

class ApiCallRepository(context: Context) {


    @Inject
    lateinit var apiInterace:APIService

    init {
        MainApplication.instance.mComponent.inject(this)
    }




}