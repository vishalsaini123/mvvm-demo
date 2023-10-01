package com.demo.common_helper


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status


class MySMSBroadcastReceiver : BroadcastReceiver() {

    private var otpReceiver: OTPReceiveListener? = null

    fun initOTPListener(receiver: OTPReceiveListener) {
        this.otpReceiver = receiver
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION == intent.action) {
            val extras = intent.extras
            if (extras != null) {
                val status = extras[SmsRetriever.EXTRA_STATUS] as Status
                //val status = extras.get(SmsRetriever.EXTRA_STATUS) as Status
                Log.d("statusValue : ", status.toString())
                when (status.statusCode) {
                    CommonStatusCodes.SUCCESS -> {
                        var otp: String = extras.get(SmsRetriever.EXTRA_SMS_MESSAGE) as String
                        Log.d("OTPReceived :", otp)
                        if (otpReceiver != null) {
                            otp = otp.replace("# Your OTP for Easy Earn app is :", "")
                                .split(":".toRegex()).dropLastWhile { it.isEmpty() }
                                .toTypedArray()[0].split(" ").dropLastWhile { it.isEmpty() }
                                .toTypedArray()[0].trim()
                            println("OTPReceivedVal :$otp")
                            otpReceiver?.onOTPReceived(otp)
                        }
                    }
                    CommonStatusCodes.TIMEOUT -> {
                        println("OTPReceived1 : " + CommonStatusCodes.TIMEOUT)
                        if (otpReceiver != null) otpReceiver?.onOTPTimeOut()
                    }
                }

                /* var otp: String = extras.get(SmsRetriever.EXTRA_SMS_MESSAGE) as String
                 println("OTPReceived : $otp")
                 if (otpReceiver != null) {
                     otp = otp.replace(": # Your OTP for Easy Earn app is :", "").split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1].trim()
                     println("OTPReceivedVal :$otp")
                     otpReceiver?.onOTPReceived(otp)
                 }*/
            }
        }
    }

    interface OTPReceiveListener {

        fun onOTPReceived(otp: String)

        fun onOTPTimeOut()
    }
}



