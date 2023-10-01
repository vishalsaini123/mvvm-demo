package com.demo.dagger


import com.demo.application.MainApplication
import com.demo.common_helper.PreferenceHelper
import com.demo.retrofit.APIService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class AppModule(private val app: MainApplication) {

    @Singleton
    @Provides
    fun provideApplication() = app

    @Singleton
    @Provides
    fun provideApiInterface(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

    @Singleton
    @Provides
    fun sharedHelper():PreferenceHelper{
        return PreferenceHelper(app)
    }

}