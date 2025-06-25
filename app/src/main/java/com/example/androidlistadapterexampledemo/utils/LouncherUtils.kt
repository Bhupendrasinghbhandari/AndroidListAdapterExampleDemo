package com.datinggazm.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore


/**
 * use to launch the new destination activity with bundle and without bundle
 * @param destinationActivity
 * @param bundle
 * if you not want to send data we can just pass
 * */

fun <T> Context.intentComponent(destinationActivity: Class<T>, bundle: Bundle?) {
    val intent = Intent(this, destinationActivity)
    if (bundle != null) {
        intent.putExtras(bundle)
    }
    this.startActivity(intent)
}


/**
 * use to launch the new destination activity and finish current activity
 * * @param destinationActivity
 *
 * */

fun <T> Context.intentComponentWithFinish(
    destinationActivity: Class<T>,
    bundle: Bundle?,
    isClearBack: Boolean = false
) {
    val intent = Intent(this, destinationActivity)
    if (bundle != null) {
        intent.putExtras(bundle)
    }
    this.startActivity(intent)
    (this as Activity).finish()
}

/**
 * Use to launch the new destination activity and finish the current activity
 * @param destinationActivity
 * @param bundle
 * @param enterAnimationResId Resource ID for the enter animation
 * @param exitAnimationResId Resource ID for the exit animation
 */
fun <T> Context.intentComponentWithNewTask(
    destinationActivity: Class<T>,
    bundle: Bundle? = null,
    enterAnimationResId: Int = android.R.anim.fade_in,
    exitAnimationResId: Int = android.R.anim.fade_out
) {
    val intent = Intent(this, destinationActivity)
        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    if (this is Activity) {
        this.overridePendingTransition(enterAnimationResId, exitAnimationResId)
    }
    if (bundle != null) {
        this.startActivity(intent, bundle)
    } else {
        this.startActivity(intent)

    }
    // Add transition animation

}


/**
 * @param context
 * An inline function is one for which the compiler copies the code from the function definition
 * directly into the code of the calling function rather than creating a separate set of instructions in memory.
 * This eliminates call-linkage overhead and can expose significant optimization opportunities. Using the "inline"
 * specifier is only a suggestion to the compiler that an inline expansion can be performed; the compiler is free to ignore the suggestion.
 * */

inline fun <reified T : Any> newIntent(context: Context): Intent = Intent(context, T::class.java)


/**
 * @param bundle
 * @param destinationActivity
 * @param requestCode
 *  this function use to launch [Activity] for result
 * */

fun <T> Activity.intentComponentForResult(
    destinationActivity: Class<T>,
    bundle: Bundle?,
    requestCode: Int
) {
    val intent = Intent(this, destinationActivity)
    if (bundle != null) {
        intent.putExtras(bundle)
    }
    this.startActivityForResult(intent, requestCode)
}


/**
 * this function is use to return the gallery intent
 * */

@SuppressLint("IntentReset")
fun getGalleryIntent(): Intent {
    val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    galleryIntent.type = "image/*"
    return galleryIntent
}

/**
 * this function is use to return the camera intent
 * @param context
 * @param photoUri
 * */

fun getCameraIntent(context: Context, photoUri: Uri?): Intent {
    val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    if (takePictureIntent.resolveActivity(context.packageManager) != null) {
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
    }
    return takePictureIntent
}





