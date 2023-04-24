package com.example.invioproject.splashActivity

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.example.invioproject.R
import com.example.invioproject.ui.activity.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        object : CountDownTimer(3000,1000){
            override fun onTick(p0: Long) {}

            override fun onFinish() {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }.start()

//
    }
}