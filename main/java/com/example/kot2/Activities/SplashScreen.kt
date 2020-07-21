package com.example.kot2.Activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.kot2.R
import com.example.kot2.WalkThrough.WalkThrough

class SplashScreen : AppCompatActivity() {
    var progressBar: ProgressBar? = null
    var progressStatus = 0
    var handler = Handler()
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        )
        setContentView(R.layout.activity_slash_screen)
        val animation = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.logo
        )
        val logo =
            findViewById<View>(R.id.logo) as ImageView
        logo.animation = animation
        Thread(object : Runnable {
            override fun run() {

                //---do some work here---
                while (progressStatus < 10) {
                    progressStatus = doSomeWork()
                }
                //---hides the progress bar---
                handler.post { //---0 - VISIBLE; 4 - INVISIBLE; 8 - GONE---
//                        progressBar.setVisibility(View.GONE);
                    startActivity(
                        Intent(
                            this@SplashScreen,
                            WalkThrough::class.java
                        )
                    )
                    finish()
                }
            }

            //---do some long running work here---
            private fun doSomeWork(): Int {
                try {
//---simulate doing some work---
                    Thread.sleep(500)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                return ++progress
            }
        }).start()
    }

    companion object {
        var progress = 0
    }
}