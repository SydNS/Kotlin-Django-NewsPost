package com.example.UTAMU.Activities

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.UTAMU.FragmentsJavaClasses.ForTheBottomNav.FirstFragment
import com.example.UTAMU.FragmentsJavaClasses.ForTheBottomNav.ForthFragment
import com.example.UTAMU.FragmentsJavaClasses.ForTheBottomNav.SecondFragment
import com.example.UTAMU.FragmentsJavaClasses.ForTheBottomNav.ThirdFragment
import com.example.UTAMU.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    var bottomNavigationView: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homeactivity)
        //        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        startActivity(new Intent(MainActivity.this,SplashIntro.class));
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        supportFragmentManager.beginTransaction().replace(R.id.activitymain, FirstFragment())
            .commit()
        setTitle(R.string.app_name)
        bottomNavigationView =
            findViewById<View>(R.id.bottomnavmenu) as BottomNavigationView
        bottomNavigationView!!.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.bnhome -> selectedFragment = FirstFragment()
                R.id.bndash -> selectedFragment = SecondFragment()
                R.id.bnplaces -> selectedFragment = ForthFragment()
                R.id.bnprofile -> selectedFragment = ThirdFragment()
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.activitymain, selectedFragment!!).commit()
            true
        }
        //        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbarMainActivity);
//        setSupportActionBar(toolbar);
    }

    override fun onBackPressed() {
        if (bottomNavigationView!!.selectedItemId != R.id.bnhome) {
            bottomNavigationView!!.selectedItemId = R.id.bnhome
        } else {
            super.onBackPressed()
        }
    }
}