package com.demo.application

import android.content.Context
import android.content.res.Resources
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.demo.R
import com.demo.dagger.AppComponent
import com.demo.dagger.AppModule
import com.demo.dagger.DaggerAppComponent
import com.demo.dagger.RetrofitModule
import com.google.firebase.analytics.FirebaseAnalytics



class MainApplication : MultiDexApplication() {
    lateinit var mComponent: AppComponent
    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    companion object {
        //trackier sdk dec api key
        lateinit var instance: MainApplication
        private val mInstance: MainApplication?
            @Synchronized get() {
                var appController: MainApplication?
                synchronized(MainApplication::class.java) {
                    appController = mInstance
                }
                return appController
            }

        //private const val devKey = "609678f49c3ac5cbf9582d5516d609678f49c3d6"
        private const val devKey = "b6ea635e-1721-467d-a856-326643edf1dc"
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        mComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .retrofitModule(RetrofitModule(instance, getString(R.string.server_url)))
            .build()
        mComponent.inject(this)
        //mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        MultiDex.install(this)


        //for testing set environment as development
        //val sdkConfig = TrackierSDKConfig(this, devKey, "production")
        //TrackierSDK.initialize(sdkConfig)
    }


    fun getComponent(): AppComponent {
        return mComponent
    }

    private fun getAppResources(): Resources {
        return instance.resources
    }

    fun getAppString(resourceId: Int): String {
        return getAppResources().getString(resourceId)
    }

    override fun onTerminate() {
        super.onTerminate()
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    fun getGlobalContext(): Context {
        return instance
    }

}