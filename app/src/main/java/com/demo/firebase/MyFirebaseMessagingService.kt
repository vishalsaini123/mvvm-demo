package com.demo.firebase

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.GsonBuilder

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class MyFirebaseMessagingService : FirebaseMessagingService() {
    private var localBroadcastManager: LocalBroadcastManager? = null
    private var notificationUtils: NotificationUtils? = null

    override fun onCreate() {
        super.onCreate()
        localBroadcastManager = LocalBroadcastManager.getInstance(applicationContext)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.from)

        // Check if message contains a notification payload.
        if (remoteMessage.notification != null) {
            Log.e(TAG, "Notification Body: " + remoteMessage.notification!!.body)
            handleNotification(remoteMessage.notification!!.body)
        }

        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Log.e(TAG, "Data Payload: " + remoteMessage.data.toString())
            try {
                val gson = GsonBuilder().create().toJson(remoteMessage.data)
                val json = JSONObject(gson)
                handleDataMessage(json)
            } catch (e: Exception) {
                Log.e(TAG, "Exception Conversion: " + e.message)
            }
        }
    }

    private fun handleNotification(message: String?) {
        if (!NotificationUtils.isAppIsInBackground(applicationContext)) {
            // app is in foreground, broadcast the push message
            val pushNotification = Intent(Config.PUSH_NOTIFICATION)
            pushNotification.putExtra("message", message)
            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification)

            // play notification sound
            val notificationUtils = NotificationUtils(applicationContext)
            notificationUtils.playNotificationSound()
        } else {
            // If the app is in background, firebase itself handles the notification
        }
    }
    private fun handleDataMessage(json: JSONObject) {
        Log.e(TAG, "push json: $json")
        try {
//            JSONObject data = json.getJSONObject("data");
            val title = if (json.has("title")) json.getString("title") else ""
            val message = if (json.has("content"))json.getString("content") else ""
            var imageUrl = if (json.has("image_path"))  json.getString("image_path") else ""
            val type_data = if (json.has("type_data")) json.getString("type_data") else ""
            val type = if (json.has("type"))json.getString("type") else ""
            Log.e(TAG, "title: $title")
            Log.e(TAG, "message: $message")
            //            Log.e(TAG, "payload: " + payload.toString());
            Log.e(TAG, "imageUrl: $imageUrl")

            /* Thread to insert data in database */

            CoroutineScope(Dispatchers.IO).launch {
              //  val notificationModel = NotificationModel(title,message,imageUrl,type_data,System.currentTimeMillis())
              //  AppDatabase.getInstance(applicationContext)!!.taskDao().insert(Task(GsonBuilder().create().toJson(notificationModel)))
            }


            var resultIntent: Intent? = null
            val bundle = Bundle()
            when (type.toLowerCase(Locale.ROOT)) {
                "c" -> {
                    Log.e(TAG, "handleDataMessage: Inside Vendor List")
                   // resultIntent = Intent(applicationContext, UserDashboardActivity::class.java)
                   /* val responseHome = ProductData()
                    responseHome.category = title
                    responseHome.category_id = type_data
                    responseHome.imageUrl = imageUrl
                   // bundle.putSerializable(ApplicationConstant.CATEGORY_DATA, responseHome)
                    resultIntent.putExtra(ApplicationConstant.CATEGORY_BUNDLE, bundle)
                    resultIntent.putExtra(ApplicationConstant.IS_FROM, IntentHelper.INTENT_CATEGORY_LIST)*/
                }
                "v" -> {
                    Log.e(TAG, "handleDataMessage: INTENT_VENDOR_DETAILS")
                   /* val vendorsBean = ProductData()
                    vendorsBean.company = title
                    vendorsBean.company_id = type_data
                    vendorsBean.imageUrl = imageUrl
                    vendorsBean.average_rating = "0"
                    resultIntent = Intent(applicationContext, DashBoardActivity::class.java)
                    bundle.putSerializable(ApplicationConstant.COMPANY_DATA, vendorsBean)
                    resultIntent.putExtra(ApplicationConstant.COMPANY_BUNDLE, bundle)
                    resultIntent.putExtra(ApplicationConstant.IS_FROM, IntentHelper.INTENT_VENDOR_DETAILS)*/
                }

                "m" -> Log.e("FD", "FCM Chat")
                else -> {
                    Log.e(TAG, "handleDataMessage: default")
                   // resultIntent = Intent(applicationContext, UserDashboardActivity::class.java)
                }
            }
            resultIntent!!.putExtra("message", message)

//            if (ChatActivity.isActive) {
//                sendPreparedBroadcast();
//            }
          //  val showNotification = AppSharedPref.getShowNotification(applicationContext)
          //  Log.e(TAG, "Notification: $showNotification")

            // check for image attachment
            if (TextUtils.isEmpty(imageUrl)) {
                if (type.equals("m", ignoreCase = true)) {
//                    if (!ChatActivity.isActive && showNotification) {
//                        showNotificationMessage(getApplicationContext(), title, message, "", resultIntent);
//                    }
                } else {
                   // if (showNotification)
                        showNotificationMessage(applicationContext, title, message, "", resultIntent)
                }
            } else {
                // image is present, show notification with image
                if (type.equals("m", ignoreCase = true)) {
//                    if (!ChatActivity.isActive && showNotification)
//                        showNotificationMessageWithBigImage(getApplicationContext(), title, message, "", resultIntent, imageUrl);
                } else {
                  //  if (showNotification)
                        showNotificationMessageWithBigImage(applicationContext, title, message, "", resultIntent, imageUrl)
                }
            }
        } catch (e: JSONException) {
            Log.e(TAG, "Json Exception: " + e.message)
        } catch (e: Exception) {
            Log.e(TAG, "Exception below: " + e.localizedMessage)
        }
    }

    private fun sendPreparedBroadcast() {
        //Intent intent = new Intent(ChatActivity.THREAD_ID);
        //localBroadcastManager.sendBroadcast(intent);
    }

    override fun onNewToken(s: String) {
        super.onNewToken(s)
        Log.e(TAG, "onNewToken: $s")
       // AppSharedPref.setFCMToken(applicationContext, s)
    }

    /**
     * Showing notification with text only
     */
    private fun showNotificationMessage(context: Context, title: String, message: String, timeStamp: String, intent: Intent?) {
        notificationUtils = NotificationUtils(context)
        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        notificationUtils!!.showNotificationMessage(title, message, timeStamp, intent)
    }

    /**
     * Showing notification with text and image
     */
    private fun showNotificationMessageWithBigImage(context: Context, title: String, message: String, timeStamp: String, intent: Intent?, imageUrl: String) {
        notificationUtils = NotificationUtils(context)
        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        notificationUtils!!.showNotificationMessage(title, message, timeStamp, intent, imageUrl)
    }

    companion object {
        private val TAG = MyFirebaseMessagingService::class.java.simpleName
    }
}