package com.demo.common_helper

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.telephony.TelephonyManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.demo.BuildConfig
import com.demo.MainActivity
import com.demo.R
import com.demo.application.MainApplication
import org.apache.commons.codec.binary.Base64
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.SimpleDateFormat
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object DefaultHelper {

    fun getManufacturer(): String {
        return Build.MANUFACTURER.toString()
    }

    fun getBrand(): String {
        return Build.BRAND.toString()
    }

    fun getDeviceModel(): String {
        return Build.MODEL.toString()
    }

    fun getBuildVersion(): String {
        return Build.VERSION.RELEASE
    }


    fun getVersionName(): String {
        return BuildConfig.VERSION_NAME
    }

    fun getVersionCode(): String {
        return BuildConfig.VERSION_CODE.toString()
    }

    fun getCpu(): String {
        return Build.CPU_ABI
    }

    fun getDisplay(): String {
        return Build.DISPLAY
    }


    fun getDeviceType(): String {
        return "phone"
    }

    @SuppressLint("HardwareIds")
    fun getDeviceId(context: Context?): String {
        return Settings.Secure.getString(context?.contentResolver, Settings.Secure.ANDROID_ID)
    }

    //to get network operator name
    fun getCarrierName(context: Context?): String {
        val tm = context?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return tm.networkOperatorName.toString()
    }


    fun showToast(context: Context?, message: String, duration: Int = Toast.LENGTH_LONG) {
        var mToast: Toast? = null
        if (message.isNotEmpty()) {
            mToast?.cancel()
            mToast = Toast.makeText(context, message, duration)
            mToast!!.show()
        }
    }

    fun hideKeyboard(context: Activity?, view: View?) {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }


    fun decrypt(plainText: String): String {
        return try {
            val base64 = Base64()
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            val secretKey =
                SecretKeySpec(ConstantHelper.secretKey.toByteArray(charset("UTF-8")), "AES")
            val initializeVectorKey = IvParameterSpec(
                ConstantHelper.initializeVectorKey.toByteArray(charset("UTF-8")),
                0,
                cipher.blockSize
            )
            cipher.init(Cipher.DECRYPT_MODE, secretKey, initializeVectorKey)
            //decrypt
            val text = cipher.doFinal(base64.decode(plainText.trim().toByteArray()))
            //println("Decrypt text : " + String(text))
            String(text)
        } catch (e: Exception) {
            plainText
        }
    }

    fun encrypt(plainText: String): String {
        val base64 = Base64()
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")

        val secretKey = SecretKeySpec(ConstantHelper.secretKey.toByteArray(charset("UTF-8")), "AES")
        val initializeVectorKey = IvParameterSpec(
            ConstantHelper.initializeVectorKey.toByteArray(charset("UTF-8")),
            0,
            cipher.blockSize
        )

        //encrypt
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, initializeVectorKey)
        val encryptedCipherBytes = base64.encode(cipher.doFinal(plainText.trim().toByteArray()))
        return String(encryptedCipherBytes)
    }

    fun forceLogout(context: FragmentActivity, msg: String) {
        if (msg.isNotEmpty() && msg != "null") {
            val msgValue = decrypt(msg)
            showToast(context, msgValue)
        }
        val preferenceHelper = PreferenceHelper(context)
        preferenceHelper.clearAllPreference()

        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
        context.finish()
    }

    fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
        val intent = Intent(this, it)
        intent.putExtras(Bundle().apply(extras))
        startActivity(intent)
    }
    fun indianRupees(str: String?): String = "₹ $str"


    @SuppressLint("ConstantLocale")
    private val SDF: SimpleDateFormat = SimpleDateFormat("yyyymmddhhmmss", Locale.getDefault())

    @Throws(IOException::class)
    fun getCompressed(context: Context?, path: String?): File? {
        if (context == null) throw NullPointerException("Context must not be null.")
        //getting device external cache directory, might not be available on some devices,
        // so our code fall back to internal storage cache directory, which is always available but in smaller quantity
        var cacheDir: File? = context.externalCacheDir
        if (cacheDir == null) //fall back
            cacheDir = context.cacheDir
        val rootDir: String = cacheDir?.absolutePath.toString() + "/ImageCompressor"
        val root = File(rootDir)

        //Create ImageCompressor folder if it doesnt already exists.
        if (!root.exists()) root.mkdirs()

        //decode and resize the original bitmap from @param path.
        val bitmap: Bitmap =
            decodeImageFromFiles(path,  /* your desired width*/300,  /*your desired height*/300)!!

        //create placeholder for the compressed image file
        val compressed = File(root, SDF.format(Date()).toString() + ".jpg")

        //convert the decoded bitmap to stream
        val byteArrayOutputStream = ByteArrayOutputStream()

        /*compress bitmap into byteArrayOutputStream
            Bitmap.compress(Format, Quality, OutputStream)
            Where Quality ranges from 1 - 100.
         */bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream)

        /*
        Right now, we have our bitmap inside byteArrayOutputStream Object, all we need next is to write it to the compressed file we created earlier,
        java.io.FileOutputStream can help us do just That!
         */
        val fileOutputStream = FileOutputStream(compressed)
        fileOutputStream.write(byteArrayOutputStream.toByteArray())
        fileOutputStream.flush()
        fileOutputStream.close()

        //File written, return to the caller. Done!
        return compressed
    }


    private fun decodeImageFromFiles(path: String?, width: Int, height: Int): Bitmap? {
        val scaleOptions = BitmapFactory.Options()
        scaleOptions.inJustDecodeBounds = true
        BitmapFactory.decodeFile(path, scaleOptions)
        var scale = 1
        while (scaleOptions.outWidth / scale / 2 >= width && scaleOptions.outHeight / scale / 2 >= height) {
            scale *= 2
        }
        // decode with the sample size
        val outOptions = BitmapFactory.Options()
        outOptions.inSampleSize = scale
        return BitmapFactory.decodeFile(path, outOptions)
    }

    private fun isPackageInstalled(c: Context, targetPackage: String): Boolean {
        val pm: PackageManager = c.packageManager
        return try {
            pm.getPackageInfo(targetPackage, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    fun share(sharingText: String?, context: Context, appPackage: String) {
        val i = Intent(Intent.ACTION_SEND)
        if (isPackageInstalled(context, appPackage)) i.setPackage(appPackage)
        i.type = "text/plain"
        i.putExtra(Intent.EXTRA_SUBJECT, context?.getString(R.string.app_name))
        i.putExtra(Intent.EXTRA_TEXT, sharingText)
        context.startActivity(
            Intent.createChooser(
                i,
                "${context.getString(R.string.app_name)} Share"
            )
        )
    }

    fun loadImage(
        context: Context?,
        imageUrl: String,
        imageView: ImageView,
        placeholder: Drawable? = ContextCompat.getDrawable(context!!, R.drawable.ic_app_logo_with),
        error: Drawable? = ContextCompat.getDrawable(context!!, R.drawable.ic_app_logo_with)
    ) {
        context?.let {
            Glide.with(it).load(imageUrl).placeholder(placeholder).error(error).into(imageView)
        }
    }

    fun getCurrentURL(context: Context): String {
        return context.getString(R.string.server_url)
    }


    @Suppress("DEPRECATION")
    fun isOnline(): Boolean {
        var connected = false
        @Suppress("LiftReturnOrAssignment")
        (MainApplication.instance.let {
            val cm = MainApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val networkCapabilities = cm.activeNetwork ?: return false
                val actNw = cm.getNetworkCapabilities(networkCapabilities) ?: return false
                connected = actNw.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            } else {
                val netInfo = cm.activeNetworkInfo
                connected = netInfo?.isConnectedOrConnecting == true
            }
        })
        return connected
    }

    fun getPackageId(context: Context?): String {
        var packageID = ""
        try {

            val packageName = context?.packageName
            val packageManager = context?.packageManager
            val signatures = packageName?.let {
                packageManager?.getPackageInfo(
                    it,
                    PackageManager.GET_SIGNATURES
                )?.signatures
            }
            // For each signature create a compatible hash
            if (signatures != null) {
                for (signature in signatures) {
                    packageID = hash(packageName.toString(), signature.toCharsString()).toString()
                }
            }

        } catch (e: PackageManager.NameNotFoundException) {
            //Log.e(TAG, "Unable to find package to obtain hash.", e)
        }
        return packageID
    }


    private fun hash(packageName: String, signature: String): String? {
        val HASH_TYPE = "SHA-256"
        val NUM_HASHED_BYTES = 9
        val NUM_BASE64_CHAR = 11
        val appInfo = "$packageName $signature"
        try {
            val messageDigest = MessageDigest.getInstance(HASH_TYPE)
            messageDigest.update(appInfo.toByteArray(StandardCharsets.UTF_8))
            var hashSignature = messageDigest.digest()

            // truncated into NUM_HASHED_BYTES
            hashSignature = Arrays.copyOfRange(hashSignature, 0, NUM_HASHED_BYTES)
            // encode into Base64
            var base64Hash = android.util.Base64.encodeToString(
                hashSignature,
                android.util.Base64.NO_PADDING or android.util.Base64.NO_WRAP
            )
            base64Hash = base64Hash.substring(0, NUM_BASE64_CHAR)

            //Log.e(TAG, String.format("pkg: %s -- hash: %s", packageName, base64Hash))
            return base64Hash
        } catch (e: NoSuchAlgorithmException) {
            //Log.e(TAG, "hash:NoSuchAlgorithm", e)
        }

        return null
    }

    fun openFragment(context: FragmentActivity, fragment: Fragment, addToBackStack: Boolean) {
        if (addToBackStack) {
          //  context.supportFragmentManager.beginTransaction().add(R.id.flMain, fragment).addToBackStack(MainActivity::class.java.simpleName).commit()

        } else {
          //  context.supportFragmentManager.beginTransaction().add(R.id.flMain, fragment).commit()
        }
    }

    fun drawableToBitmap(drawable: Drawable?): Bitmap {
        var bitmap: Bitmap? = null
        if (drawable is BitmapDrawable) {
            if (drawable.bitmap != null) {
                return drawable.bitmap
            }
        }
        bitmap = if (drawable!!.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
            Bitmap.createBitmap(
                1,
                1,
                Bitmap.Config.ARGB_8888
            ) // Single color bitmap will be created of 1x1 pixel
        } else {
            Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
        }
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
        drawable.draw(canvas)
        return bitmap
    }

   fun showMsg(msg:String){
       showToast(MainApplication.instance,msg,Toast.LENGTH_SHORT)
   }

}