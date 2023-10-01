package com.demo.common_helper

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import com.demo.BuildConfig
import java.text.SimpleDateFormat
import java.util.*

object ConstantHelper {


    const val onNext = "Next"
    const val onPrevious = "Previous"
    const val onPlay = "Play"
    const val onPausePlayer = "Pause"
    const val onSeek = "Seek"
    const val onMoveSeek = "MoveSeek"
    const val setMaxProgress = "SetMax"
    const val secretKey = "KEY4256489213459"
    const val initializeVectorKey = "$3asdHJK%450#xs!"

    const val preferenceName = "EasyEarnApplication"
    const val failed = 500
    const val success = 200
    const val forceLogout = 0
    const val apiFailed = 2
    const val noInternet = 3
    const val authorizationFailed = 4
    val dateFormat: SimpleDateFormat = SimpleDateFormat("dd", Locale.US)
     val monthFormat: SimpleDateFormat = SimpleDateFormat("MM", Locale.US)
     val yearFormat: SimpleDateFormat = SimpleDateFormat("yyyy", Locale.US)
     val formatDate: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)

    const val serverSideFormat = "yyyy-MM-dd HH:mm:ss"

    const val serverReadTimeOut = 60
    const val serverConnectTimeOut = 60

    const val male = "1"
    const val female = "2"

    const val playStoreLink = "https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}"

    //social platforms package id
    const val FACEBOOK = "com.facebook.orca"
    const val TWITTER = "com.twitter.android"
    const val GMAIL = "com.google.android.gm"
    const val WHATSAPP = "com.whatsapp"

    //Login Type
    const val User = "user"
    const val Listener = "listener"
    const val app_login = "0"
    const val true_caller_login = "1"

    const val VIEW_TYPE_ITEM = 0
    const val VIEW_TYPE_LOADING = 1

    const val validationSuccessful = "Validation Successful"

    fun View.margin(left: Float? = null, top: Float? = null, right: Float? = null, bottom: Float? = null) {
        layoutParams<ViewGroup.MarginLayoutParams> {
            left?.run { leftMargin = dpToPx(this) }
            top?.run { topMargin = dpToPx(this) }
            right?.run { rightMargin = dpToPx(this) }
            bottom?.run { bottomMargin = dpToPx(this) }
        }
    }

    inline fun <reified T : ViewGroup.LayoutParams> View.layoutParams(block: T.() -> Unit) {
        if (layoutParams is T) block(layoutParams as T)
    }

    fun View.dpToPx(dp: Float): Int = context.dpToPx(dp)
    fun Context.dpToPx(dp: Float): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()
}