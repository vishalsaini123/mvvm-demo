package com.demo.common_helper

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import okhttp3.Cookie
import java.util.*

open class PreferenceHelper(context: Context) {

    private val sharedPreferences: SharedPreferences

    init {
        val preference = ConstantHelper.preferenceName
        this.sharedPreferences = context.getSharedPreferences(preference, Context.MODE_PRIVATE)
    }


    fun setKey(key: String, value: String) {
        this.sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String): String {
        return this.sharedPreferences.getString(key, "").toString()
    }

    enum class Key {
        UserLoggedIn, AuthorizationKey, UserId, FcmToken, IpAddress, UserFullName, UserLastName, UserEmail, UserMobile, UserProfileImage,
        UserDob, UserGender, Referral, Language, InviteCode, State, StateCode, City, EmailVerifyStatus, WalletAmount,UserType

    }

    fun setJwtToken(value: String) {
        this.sharedPreferences.edit().putString(Key.AuthorizationKey.name, value).apply()
    }

    fun getJwtToken(): String {
        return this.sharedPreferences.getString(Key.AuthorizationKey.name, "").toString()
    }


    fun setLoggedIn(value: Boolean) {
        this.sharedPreferences.edit().putBoolean(Key.UserLoggedIn.name, value).apply()
    }

    fun getLoggedIn(): Boolean {
        return this.sharedPreferences.getBoolean(Key.UserLoggedIn.name, false)
    }


    fun setFcmToken(value: String) {
        this.sharedPreferences.edit().putString(Key.FcmToken.name, value).apply()
    }

    fun getFcmToken(): String {
        return this.sharedPreferences.getString(Key.FcmToken.name, "").toString()
    }

    fun setBoolean(key: String, value: Boolean) {
        this.sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String): Boolean {
        return this.sharedPreferences.getBoolean(key, false)
    }

    internal fun clearAllPreference() {
        this.sharedPreferences.edit().clear().apply()
    }

    open fun setClearSpecific(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    fun setInt(key: String, value: Int) {
        this.sharedPreferences.edit().putInt(key, value).apply()
    }

    fun getInt(key: String): Int {
        return this.sharedPreferences.getInt(key, 0)
    }

    internal fun setCookies(base_url: String, cookies: List<Cookie>) {
        // Convert cookies to JSON and store them
        val gson = Gson()
        val cookiesString = gson.toJson(cookies)
        this.sharedPreferences.edit().putString(base_url, cookiesString).apply()
    }

    internal fun getCookies(base_url: String): List<Cookie> {
        val gson = Gson()
        val cookiesString = this.sharedPreferences.getString(base_url, null)
        return if (cookiesString != null) {
            Arrays.asList(*gson.fromJson(cookiesString, Array<Cookie>::class.java))
        } else ArrayList()
    }

    fun setUserType(value: String) {
        this.sharedPreferences.edit().putString(Key.UserType.name, value).apply()
    }

    fun getUserType():String {
       return this.sharedPreferences.getString(Key.UserType.name, "").toString()
    }
    fun setWalletAmount(value: String) {
        this.sharedPreferences.edit().putString(Key.WalletAmount.name, value).apply()
    }

    fun getWalletAmount(): String {
        return this.sharedPreferences.getString(Key.WalletAmount.name, "").toString()
    }

    fun setIpAddress(value: String) {
        this.sharedPreferences.edit().putString(Key.IpAddress.name, value).apply()
    }

    fun getIpAddress(): String {
        return this.sharedPreferences.getString(Key.IpAddress.name, "").toString()
    }

    fun getUserId(): String {
        return this.sharedPreferences.getString(Key.UserId.name, "").toString()
    }

    fun setUserId(value: String) {
        this.sharedPreferences.edit().putString(Key.UserId.name, value).apply()
    }

    fun setFullName(value: String) {
        this.sharedPreferences.edit().putString(Key.UserFullName.name, value).apply()
    }

    fun getFullName(): String {
        return this.sharedPreferences.getString(Key.UserFullName.name, "").toString()
    }

    fun setEmail(value: String) {
        this.sharedPreferences.edit().putString(Key.UserEmail.name, value).apply()
    }

    fun getEmail(): String {
        return this.sharedPreferences.getString(Key.UserEmail.name, "").toString()
    }

    fun setMobileNumber(value: String) {
        this.sharedPreferences.edit().putString(Key.UserMobile.name, value).apply()
    }

    fun getMobileNumber(): String {
        return this.sharedPreferences.getString(Key.UserMobile.name, "").toString()
    }

    fun setProfilePic(value: String) {
        this.sharedPreferences.edit().putString(Key.UserProfileImage.name, value).apply()
    }

    fun getProfilePic(): String {
        return this.sharedPreferences.getString(Key.UserProfileImage.name, "").toString()
    }

    fun setUserDob(value: String) {
        this.sharedPreferences.edit().putString(Key.UserDob.name, value).apply()
    }

    fun getUserDob(): String {
        return this.sharedPreferences.getString(Key.UserDob.name, "").toString()
    }

    fun setUserGender(value: String) {
        this.sharedPreferences.edit().putString(Key.UserGender.name, value).apply()
    }

    fun getUserGender(): String {
        return this.sharedPreferences.getString(Key.UserGender.name, "").toString()
    }

    //Which User will share to others
    fun setReferral(value: String) {
        this.sharedPreferences.edit().putString(Key.Referral.name, value).apply()
    }

    fun getReferral(): String {
        return this.sharedPreferences.getString(Key.Referral.name, "").toString()
    }

    //Which User will Enter at the time of Registration
    fun setInviteCode(value: String) {
        this.sharedPreferences.edit().putString(Key.InviteCode.name, value).apply()
    }

    fun getInviteCode(): String {
        return this.sharedPreferences.getString(Key.InviteCode.name, "").toString()
    }

    fun setLanguage(value: String) {
        this.sharedPreferences.edit().putString(Key.Language.name, value).apply()
    }

    fun getLanguage(): String {
        return this.sharedPreferences.getString(Key.Language.name, "").toString()
    }

    fun setState(value: String) {
        this.sharedPreferences.edit().putString(Key.State.name, value).apply()
    }

    fun getState(): String {
        return this.sharedPreferences.getString(Key.State.name, "").toString()
    }

    fun setStateCode(value: String) {
        this.sharedPreferences.edit().putString(Key.StateCode.name, value).apply()
    }

    fun getStateCode(): String {
        return this.sharedPreferences.getString(Key.StateCode.name, "").toString()
    }

    fun setCity(value: String) {
        this.sharedPreferences.edit().putString(Key.City.name, value).apply()
    }

    fun getcity(): String {
        return this.sharedPreferences.getString(Key.City.name, "").toString()
    }

    fun setEmailVerifyStatus(value: String) {
        this.sharedPreferences.edit().putString(Key.EmailVerifyStatus.name, value).apply()
    }

    fun getEmailVerifyStatus(): String {
        return this.sharedPreferences.getString(Key.EmailVerifyStatus.name, "").toString()
    }
}