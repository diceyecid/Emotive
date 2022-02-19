package com.example.emotive

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_mood_report.*

class MoodReportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_report)
        mrPrevImage.setOnClickListener {
            finish()
        }

        face1Image.setOnClickListener {
            val intent = Intent (this,
                TextEntryActivity::class.java)

            intent.putExtra("face", "1")
            startActivity(intent)
        }

        face2Image.setOnClickListener {
            val intent = Intent (this,
                TextEntryActivity::class.java)

            intent.putExtra("face", "2")
            startActivity(intent)
        }
        face3Image.setOnClickListener {
            val intent = Intent (this,
                TextEntryActivity::class.java)

            intent.putExtra("face", "3")
            startActivity(intent)
        }
        face4Image.setOnClickListener {
            val intent = Intent (this,
                TextEntryActivity::class.java)

            intent.putExtra("face", "4")
            startActivity(intent)
        }
        face5Image.setOnClickListener {
            val intent = Intent (this,
                TextEntryActivity::class.java)

            intent.putExtra("face", "5")
            startActivity(intent)
        }
        /*
        prevImage.setOnClickListener {
            val intent = Intent (this,
                MainActivity::class.java)

            startActivity(intent)
        }

         */
    }
}