package com.projeto.teste_sofie

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Splash : AppCompatActivity() {

    var splashDelay: Handler = Handler()
    var nextScreen: Class<*>? = null
    var splashTime: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initialLoading()

    }

    fun initialLoading(){
        splashDelay.postDelayed(delay, 2000)
        nextScreen = HomeActivity::class.java

    }

    fun showNext() {

        if (splashTime) {

            val intent = Intent(this, nextScreen)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

    }


    val delay = Runnable {
        splashTime = true
        showNext()
    }
}