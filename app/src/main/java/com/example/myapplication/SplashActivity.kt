package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment


class SplashActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash)
//        Handler().postDelayed(Runnable {
//
//
//            val navHostFragment: NavHostFragment = supportFragmentManager.findFragmentById( R.id.nav_host_fragment ) as NavHostFragment
//
//            navHostFragment = NavHostFragment.GONE
//
//                    }, 4000)
        Handler().postDelayed(Runnable {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()        }, 4000)

    }
}
