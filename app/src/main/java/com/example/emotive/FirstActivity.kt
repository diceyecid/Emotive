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
        var userData = UserData.getInstance( this )
        userData.unlockItem(Item(1,30, "@drawable/ava_fox", 3 ))
        userData.avatar=Item(1,30, "@drawable/ava_fox", 3 )
        userData.background=Item(-1,0, "@drawable/empty_icon", -1 )
        userData.decoration=Item(-1,0, "@drawable/empty_icon", -1 )
        arrow2Button.setOnClickListener {
            val intent = Intent (this,
                MoodReportActivity::class.java)
            startActivity(intent)
        }
    }
}