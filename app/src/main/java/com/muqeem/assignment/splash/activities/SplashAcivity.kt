package com.muqeem.assignment.splash.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.kotlinrnd.base.views.BaseActivity
import com.muqeem.assignment.R
import com.muqeem.assignment.home.activities.MainActivity

class SplashAcivity : BaseActivity() {
    private val SPLASH_TIME_OUT:Long=3000 // 3 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}