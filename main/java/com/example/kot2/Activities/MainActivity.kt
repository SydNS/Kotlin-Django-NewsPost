package com.example.kot2.Activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kot2.R
import com.example.kot2.R.color.colorPrimary
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //equivalent to public static in java
    companion object{
        const val KEYNAME="names"
    }

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener(){
            var pnames= names.text.toString()
            Toast.makeText(this,pnames,Toast.LENGTH_LONG).show()

            startActivity(Intent(this, Second::class.java).putExtra(
                KEYNAME,pnames))
        }

        tv1.setOnClickListener(){
            button1.setBackgroundColor(colorPrimary)
            startActivity(Intent(this, VolKo::class.java))
        }


    }
}