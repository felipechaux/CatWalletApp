package com.chauxdevapps.catwalletapp.framework.image

import android.widget.ImageView
import coil.load
import javax.inject.Inject
import javax.inject.Singleton

interface ImageManager {
    fun loadImage(url: String, imageView: ImageView)
}

@Singleton
class CoilImageManager @Inject constructor() : ImageManager {
    override fun loadImage(url: String, imageView: ImageView) {
        imageView.load(url) {
            crossfade(true)
        }
    }
}
