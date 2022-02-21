package com.example.emotive

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_mood_data.*


class MoodDataActivity : AppCompatActivity()
{
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate( savedInstanceState: Bundle? )
    {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_mood_data )

        viewDate.text = "Today"
    }
}