package com.demo.common_helper

import android.util.Patterns
import android.widget.Toast
import com.demo.application.MainApplication
import java.util.regex.Pattern

object Validation {

    fun loginValidate(phone:String):Boolean{
        return if (phone.isEmpty()) {
            showMsg("Please enter mobile number")
            false
        } else if (!isValidMobile(phone)) {
            showMsg("Mobile number is invalid")
            false
        } else true
    }
    fun registerValidate(phone:String):Boolean{
        return if (phone.isEmpty()) {
            showMsg("Please enter mobile number")
            false
        } else if (!isValidMobile(phone)) {
            showMsg("Mobile number is invalid")
            false
        } else true
    }

    fun registerApplyingHelpValidate(name:String,email:String,audiopath:String,qualification:String,information:Boolean,
    available:Boolean,termscondition:Boolean):Boolean{
        return if (name.isEmpty()) {
            showMsg("Please enter your full name")
            false
        } else if (email.isEmpty()) {
            showMsg("Please enter your email id")
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showMsg("Please enter a valid email id")
            false
        } else if (audiopath.isEmpty()) {
            showMsg("Please select audio file")
            false
        } else if (qualification.isEmpty()) {
            showMsg("Please enter your qualification")
            false
        } else if (!information) {
            showMsg("Please complete the form")
            false
        } else if (!available) {
            showMsg("Please complete the form")
            false
        } else if (!termscondition) {
            showMsg("Please complete the form")
            false
        }

        else true
    }

    fun raiseTicketValidate(name:String,email:String,problem:String):Boolean{
        return if (name.isEmpty()) {
            showMsg("Please enter your  name")
            false
        } else if (email.isEmpty()) {
            showMsg("Please enter your email id")
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showMsg("Please enter a valid email id")
            false
        } else if (problem.isEmpty()) {
            showMsg("Please enter your problem")
            false
        }
        else true
    }

    fun genderValidate(currentDate:String):Boolean{
        return if (currentDate.isEmpty()) {
            showMsg("Please enter date")
            false
        }  else true
    }

    fun otpValidate(otp1:String,otp2:String,otp3:String,otp4:String):Boolean{
        return when {
            otp1.isEmpty() -> {
                showMsg("Invalid Otp.")
                false }
            otp2.isEmpty() -> {
                showMsg("Invalid Otp.")
                false
            }
            otp3.isEmpty() -> {
                showMsg("Invalid Otp.")
                false
            }
            otp4.isEmpty() -> {
                showMsg("Invalid Otp.")
                false
            }
            else -> true
        }
    }


    private fun isValidMobile(phone: String): Boolean {
        return if (!Pattern.matches("[a-zA-Z]+", phone)) {
            phone.length in 10..12
        } else false
    }
    private fun showMsg(message:String){
        Toast.makeText(MainApplication.instance,message,Toast.LENGTH_LONG).show()
    }



}