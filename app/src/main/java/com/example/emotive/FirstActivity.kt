package com.example.emotive

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_first.*
import kotlinx.android.synthetic.main.activity_intro.*

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        Utils.hideSystemBars( window )
        arrow2Button.setOnClickListener {
            val intent = Intent (this,
                MoodReportActivity::class.java)
            startActivity(intent)
        }
    }
}