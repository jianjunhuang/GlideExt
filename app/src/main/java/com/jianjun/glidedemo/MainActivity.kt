package com.jianjun.glidedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jianjun.glidedemo.databinding.ActivityMainBinding
import com.jianjun.glidedemo.transition.TransitionActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnTransition.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val sIntent = Intent()
        sIntent.setClass(
            this, when (p0?.id) {
                else -> {
                    TransitionActivity::class.java
                }
            }
        )
        startActivity(sIntent)
    }
}