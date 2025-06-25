package com.example.androidlistadapterexampledemo.utils


import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.androidlistadapterexampledemo.R
import de.hdodenhof.circleimageview.CircleImageView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getCurrentFormattedDate(): String {
    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    return dateFormat.format(Date())
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.countrydelight)
                .error(R.drawable.countrydelight)
                .centerCrop()
        )
        .into(this)
}

fun progressBar(context: Context): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    return circularProgressDrawable
}


///**
// * Set image to image view
// * @param image
// * */
//fun ImageView.glideImage(image: Any?, placeholder: Int? = null) {
////    val progressBar = progressBar(context).apply {
////        setTintList(ContextCompat.getColorStateList(context, R.color.green))
////        setTint(ContextCompat.getColor(context, R.color.green))
////        start()
////    }
//    Glide.with(context)
//        .load(image ?: "")
//        .apply(
//            RequestOptions()
//                .diskCacheStrategy(DiskCacheStrategy.DATA) // Cache only processed images
//                .format(DecodeFormat.PREFER_RGB_565) // Use RGB_565 for lower memory usage
//                .downsample(DownsampleStrategy.AT_MOST) // Scale down large images
//                .override(800, 800) // Resize to a reasonable size
//        )
//        .thumbnail(0.1f) // Load a low-resolution preview first
//        .skipMemoryCache(true) // Avoid memory cache if images load slowly on first load
//        .placeholder(progressBar)
//        .error(placeholder ?: R.drawable.countrydelight)
//        .listener(object : RequestListener<Drawable> {
//            override fun onLoadFailed(
//                e: GlideException?,
//                model: Any?,
//                target: Target<Drawable>,
//                isFirstResource: Boolean
//            ): Boolean {
//                Log.e("GlideError", "Image Load Failed: ${e?.localizedMessage}")
//                progressBar.stop()
//                return false
//            }
//            override fun onResourceReady(
//                resource: Drawable,
//                model: Any,
//                target: Target<Drawable>?,
//                dataSource: DataSource,
//                isFirstResource: Boolean
//            ): Boolean {
//                this@glideImage.setImageDrawable(resource)
//                progressBar.stop()
//                return false
//            }
//        })
//        .into(this)
//}
//











fun CircleImageView.glideImageCircular(image: Any?, placeholder: Int? = null) {
    val progressBar = progressBar(context).apply {
        setTintList(ContextCompat.getColorStateList(context, R.color.green))
        setTint(ContextCompat.getColor(context, R.color.green))
        start()
    }

    Glide.with(context)
        .load(image ?: "")
        .apply(
            RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.DATA) // Cache only processed images
                .format(DecodeFormat.PREFER_RGB_565) // Use RGB_565 for lower memory usage
                .downsample(DownsampleStrategy.AT_MOST) // Scale down large images
                .override(800, 800) // Resize to a reasonable size
        )
        .thumbnail(0.1f) // Load a low-resolution preview first
        .skipMemoryCache(true) // Avoid memory cache if images load slowly on first load
        .placeholder(progressBar)
        .error(placeholder ?: R.drawable.gradient_vip_background)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>,
                isFirstResource: Boolean
            ): Boolean {
                Log.e("GlideError", "Image Load Failed: ${e?.localizedMessage}")
                progressBar.stop()
                return false

            }

            override fun onResourceReady(
                resource: Drawable,
                model: Any,
                target: Target<Drawable>?,
                dataSource: DataSource,
                isFirstResource: Boolean
            ): Boolean {
                this@glideImageCircular.setImageDrawable(resource)
                progressBar.stop()
                return false
            }

        })
        .into(this)
}


//fun LottieAnimationView.changeLayersColor(
//    @ColorRes colorRes: Int,
//) {
//    val color = ContextCompat.getColor(context, colorRes)
//    val filter = SimpleColorFilter(color)
//    val keyPath = KeyPath("**")
//    val callback: LottieValueCallback<ColorFilter> = LottieValueCallback(filter)
//    addValueCallback(keyPath, LottieProperty.COLOR_FILTER, callback)
//}