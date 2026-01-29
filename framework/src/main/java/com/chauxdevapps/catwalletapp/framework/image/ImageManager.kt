package com.chauxdevapps.catwalletapp.framework.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import javax.inject.Inject
import javax.inject.Singleton

interface ImageManager {
    fun loadImage(url: String, imageView: ImageView)
}

@Singleton
class GlideImageManager @Inject constructor() : ImageManager {
    override fun loadImage(url: String, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }
}
