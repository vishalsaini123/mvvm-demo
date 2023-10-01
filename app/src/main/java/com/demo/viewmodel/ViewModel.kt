package com.demo.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.demo.application.MainApplication
import com.demo.common_helper.DefaultHelper.showMsg
import com.demo.common_helper.InputParams
import com.demo.pojo.*
import com.demo.retrofit.APIService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class ViewModel(application: Application) :AndroidViewModel(application) {

    @Inject
    lateinit var api:APIService




    init {

        (application as MainApplication).getComponent().inject(this)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()

    }
    lateinit var disposable:Disposable
    val getLoginResponse = MutableLiveData<UserLoginModel>()
    val getSignUpResponse = MutableLiveData<UserSignUpModel>()
    val getuserRegistrationResponse = MutableLiveData<UserRegistrationExtraModel>()
    val getVerifyOtpResponse = MutableLiveData<UserVerifyOtpModel>()
    val getCategoriesResponse = MutableLiveData<CategoriesModel>()
    val isLoading = MutableLiveData<Boolean>()

    fun login(phone:String,userType: String){
        val inputParams = InputParams()
        inputParams.mobile = phone
        inputParams.user_type = userType
        api.    userLogin(inputParams).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io())
            .subscribe(object : Observer<UserLoginModel>{
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                    isLoading.value = true
                }

                override fun onNext(t: UserLoginModel) {

                    if (t.status==200)
                    {
                        getLoginResponse.value = t
                        showMsg(t.message!!)
                    }
                    else{
                        showMsg(t.message!!)
                    }


                }

                override fun onError(e: Throwable) {
                    Log.e("onErr",e.message!!)

                    isLoading.value = false
                    showMsg(e.localizedMessage!!)
                }

                override fun onComplete() {
                    isLoading.value = false

                }

            })
    }
    fun signUp(phone:String,userType:String){
        val inputParams = InputParams()
        inputParams.mobile = phone
        inputParams.user_type = userType
        api.userSignUp(inputParams).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io())
            .subscribe(object : Observer<UserSignUpModel>{
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                    isLoading.value = true
                }

                override fun onNext(t: UserSignUpModel) {

                    if (t.status==200)
                    {
                        getSignUpResponse.value = t
                        showMsg(t.message!!)
                    }
                    else{
                        showMsg(t.message!!)
                    }


                }

                override fun onError(e: Throwable) {
                    Log.e("onErr",e.message!!)

                    isLoading.value = false
                    showMsg(e.message.toString())

                }

                override fun onComplete() {
                    isLoading.value = false

                }

            })
    }
    fun userRegistrationApi(phone:String,gender:String,dob:String,categoryId:String){
        val inputParams = InputParams()
        inputParams.mobile = phone
        inputParams.gender = gender
        inputParams.dob = dob
        inputParams.categoryid = categoryId
        api.userRegistration(inputParams).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io())
            .subscribe(object : Observer<UserRegistrationExtraModel>{
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                    isLoading.value = true
                }

                override fun onNext(t: UserRegistrationExtraModel) {

                    if (t.status==200)
                    {
                        getuserRegistrationResponse.value = t
                        showMsg(t.message!!)
                    }
                    else{
                        showMsg(t.message!!)
                    }


                }

                override fun onError(e: Throwable) {
                    Log.e("onErr",e.message!!)

                    isLoading.value = false
                    showMsg(e.message.toString())

                }

                override fun onComplete() {
                    isLoading.value = false

                }

            })
    }
    private fun imagePrepareFilePart(fileName: File): MultipartBody.Part {
        val requestFile = fileName.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("audiofile", fileName.name, requestFile)
    }
    private fun audioPrepareFilePart(fileName: File): MultipartBody.Part {
        val requestFile = fileName.asRequestBody("audio/*".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("audiofile", fileName.name, requestFile)
    }
    fun listenerRegistrationApi(phone:String,name:String,email:String,qualification:String,informationcorrect:String,availablefor3hours:String,termsconditions:String,userType: String,gender:String,dob:String,categoryId:String,path:String){
        val rb_phone = phone.toRequestBody("text/plain".toMediaTypeOrNull())
        val rb_name = name.toRequestBody("text/plain".toMediaTypeOrNull())
        val rb_email = email.toRequestBody("text/plain".toMediaTypeOrNull())
        val rb_gender = gender.toRequestBody("text/plain".toMediaTypeOrNull())
        val rb_dob = dob.toRequestBody("text/plain".toMediaTypeOrNull())
        val rb_categoryid = categoryId.toRequestBody("text/plain".toMediaTypeOrNull())
        val rb_qualification = qualification.toRequestBody("text/plain".toMediaTypeOrNull())
        val rb_informationcorrect = informationcorrect.toRequestBody("text/plain".toMediaTypeOrNull())
        val rb_availablefor3hours = availablefor3hours.toRequestBody("text/plain".toMediaTypeOrNull())
        val rb_termsconditions = termsconditions.toRequestBody("text/plain".toMediaTypeOrNull())
        val rb_usertype = userType.toRequestBody("text/plain".toMediaTypeOrNull())

        val file = File(path)
        val audiopart = audioPrepareFilePart(file)
        api.registerlistener(file = audiopart,rb_phone,rb_name, email = rb_email, qualification = rb_qualification,
        gender = rb_gender, dob = rb_dob, categoryid = rb_categoryid, user_type = rb_usertype, informationcorrect = rb_informationcorrect, availablefor3hours = rb_availablefor3hours, termsconditions = rb_termsconditions).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io())
            .subscribe(object : Observer<UserRegistrationExtraModel>{
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                    isLoading.value = true
                }

                override fun onNext(t: UserRegistrationExtraModel) {

                    if (t.status==200)
                    {
                        getuserRegistrationResponse.value = t
                        showMsg(t.message!!)
                    }
                    else{
                        showMsg(t.message!!)
                    }


                }

                override fun onError(e: Throwable) {
                    Log.e("onErr",e.localizedMessage!!)
                    Log.e("onErr",e.stackTraceToString()!!)

                    isLoading.value = false
                    showMsg(e.message.toString())

                }

                override fun onComplete() {
                    isLoading.value = false

                }

            })
    }
    fun verifyOtp(phone:String,otp:String){
        val inputParams = InputParams()
        inputParams.mobile = phone
        inputParams.otp = otp
        api.userVerifyOtp(inputParams).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io())
            .subscribe(object : Observer<UserVerifyOtpModel>{
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                    isLoading.value = true
                }

                override fun onNext(t: UserVerifyOtpModel) {

                    if (t.status==200)
                    {
                        getVerifyOtpResponse.value = t
                        showMsg(t.data!!.message!!)
                    }
                    else{
                        showMsg(t.data!!.message!!)
                    }


                }

                override fun onError(e: Throwable) {

                    isLoading.value = false
                    showMsg(e.message.toString())

                }

                override fun onComplete() {
                    isLoading.value = false

                }

            })
    }

    fun getCategories(){
        api.getCategories().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io())
            .subscribe(object : Observer<CategoriesModel>{
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                    isLoading.value = true
                }

                override fun onNext(t: CategoriesModel) {

                    if (t.status==200)
                    {
                        getCategoriesResponse.value = t
                        showMsg(t.message!!)
                    }
                    else{
                        showMsg(t.message!!)
                    }


                }

                override fun onError(e: Throwable) {

                    isLoading.value = false
                    showMsg(e.message.toString())

                }

                override fun onComplete() {
                    isLoading.value = false

                }

            })
    }
}