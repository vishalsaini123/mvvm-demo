package com.demo.dagger


import com.demo.MainActivity
import com.demo.application.MainApplication
import com.demo.repository.ApiCallRepository
import com.demo.viewmodel.ViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RetrofitModule::class])
interface AppComponent {

    fun inject(application: MainApplication)
    fun inject(apiCallRepository: ApiCallRepository)
    fun inject(mainActivity: MainActivity)
    fun inject(viewModel: ViewModel)





}