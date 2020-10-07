package com.jianjun.glidedemo.transition

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.transition.DrawableCrossFadeTransition
import com.bumptech.glide.request.transition.TransitionFactory
import com.jianjun.glidedemo.databinding.ActivityTransitionBinding

class TransitionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransitionBinding

    private var index = 0
    private val images = arrayOf(
        "https://images.unsplash.com/photo-1593643946890-b5b85ade6451?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1529&q=80",
        "https://images.unsplash.com/photo-1601758004584-903c2a9a1abc?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80",
        "https://images.unsplash.com/photo-1593642634443-44adaa06623a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1250&q=80",
        //error image
        "https://images.unsplash.com/photo--44adaa06623a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1250&q=80"
    )

    //避免在加载缓存过的图片时，过渡动画消失
    private var factory = TransitionFactory<Drawable> { dataSource, isFirstResource ->
        DrawableCrossFadeTransition(1000, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransitionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSwitch.setOnClickListener {
            Glide.with(this)
                .load(images[(index++ % images.size)])
                .placeholder(ColorDrawable(Color.GRAY))
                .error(ColorDrawable(Color.DKGRAY))
                .transition(DrawableTransitionOptions.with(factory))
//                .into(binding.ivResult)
                .into(TransitionImageTarget(binding.ivResult))
        }
    }

}