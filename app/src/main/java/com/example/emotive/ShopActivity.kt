package com.example.emotive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_mood_report.*
import kotlinx.android.synthetic.main.activity_mood_report.mrPrevImage
import kotlinx.android.synthetic.main.activity_shop.*

class ShopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        petalTextView.text = intent.getStringExtra("petalNum")

        mrPrevImage.setOnClickListener {
            finish()
        }
    }
}