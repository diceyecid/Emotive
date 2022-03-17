package com.example.emotive

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // navigates to mood report
        triggerImage.setOnClickListener {
            val intent = Intent (this,
                MoodReportActivity::class.java)

            startActivity(intent)
        }

        // navigates to mood data
        chartImage.setOnClickListener {
            val intent = Intent (this,
                MoodDataActivity::class.java)

            startActivity( intent )
        }

        // navigates to the shop
        shopImage.setOnClickListener{
            intent = Intent (this,
                ShopActivity::class.java)
            intent.putExtra("petalNum", petalText.text)

            startActivity( intent )
        }

        // navigates to the reward pop up window
        userImage.setOnClickListener {
            val customdialog = Dialog(this )
            customdialog.setContentView(R.layout.activity_reward_gain)
            customdialog.show()
        }



    }
}


