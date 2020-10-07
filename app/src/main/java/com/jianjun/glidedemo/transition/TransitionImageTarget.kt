package com.jianjun.glidedemo.transition

import android.graphics.Bitmap
import android.graphics.drawable.Animatable
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.bumptech.glide.request.transition.Transition.ViewAdapter

class TransitionImageTarget(val imageView: ImageView) :
    CustomViewTarget<ImageView, Drawable>(imageView), ViewAdapter {

    private var animatable: Animatable? = null

    override fun onLoadFailed(errorDrawable: Drawable?) {
        setResourceInternal(null)
        setDrawable(errorDrawable)
    }

    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
        var dst: Drawable = resource
        //避免Glide回收，造成
        //java.lang.RuntimeException: Canvas: trying to use a recycled bitmap android.graphics.Bitmap@xxxxxx
        if (resource is BitmapDrawable) {
            val bmp = resource.bitmap
            dst = BitmapDrawable(imageView.resources, bmp.copy(bmp.config, true))
        }
        if (transition == null || !transition.transition(dst, this)) {
            setResourceInternal(dst)
        } else {
            maybeUpdateAnimatable(dst)
        }
    }

    override fun onResourceCleared(placeholder: Drawable?) {
        animatable?.stop()
    }

    private fun setResourceInternal(resource: Drawable?) {
        setDrawable(resource)
        maybeUpdateAnimatable(resource)
    }

    private fun maybeUpdateAnimatable(resource: Drawable?) {
        if (resource is Animatable) {
            animatable = resource as Animatable?
            animatable?.start()
        } else {
            animatable = null
        }
    }

    override fun getCurrentDrawable(): Drawable? {
        return imageView.drawable
    }

    override fun setDrawable(drawable: Drawable?) {
        imageView.setImageDrawable(drawable)
    }

}