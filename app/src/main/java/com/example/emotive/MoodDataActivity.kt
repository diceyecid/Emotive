package com.example.emotive

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.os.Bundle
import com.example.emotive.databinding.ActivityMoodDataBinding


class MoodDataActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMoodDataBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate( savedInstanceState: Bundle? )
    {
        super.onCreate( savedInstanceState )

        binding = ActivityMoodDataBinding.inflate( layoutInflater )
        setContentView( binding.root )
    }
}