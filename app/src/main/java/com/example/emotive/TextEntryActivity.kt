package com.example.emotive

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_text_entry.*

class TextEntryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_entry)

        val newFace = intent.getStringExtra("face")

        when(newFace){
            "1" -> {
                moodFace.setImageResource(R.drawable.tier01)
                println("This is 2")
            }

            "2" -> {
                moodFace.setImageResource(R.drawable.tier02)
                println("When x is any number from 3,4,5,6,7,8")
            }
            "3" -> {
                moodFace.setImageResource(R.drawable.tier03)
                println("When x is any number from 3,4,5,6,7,8")
            }
            "4" -> {
                moodFace.setImageResource(R.drawable.tier04)
                println("When x is any number from 3,4,5,6,7,8")
            }
            "5" -> {
                moodFace.setImageResource(R.drawable.tier05)
                println("When x is any number from 3,4,5,6,7,8")
            }
            else -> {
                println("Error")
            }

        }

        tePrevImage.setOnClickListener {
            finish()
        }
        doneButtonImage.setOnClickListener {
            val intent = Intent (this,
                MainActivity::class.java)
            startActivity(intent)
        }
    }
}