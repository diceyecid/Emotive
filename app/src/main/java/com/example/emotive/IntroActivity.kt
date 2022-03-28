package com.example.emotive

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_intro.*
import kotlinx.android.synthetic.main.activity_main.*

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        arrowButton.setOnClickListener {
            val intent = Intent (this,
                FirstActivity::class.java)
            startActivity(intent)
        }
    }
}