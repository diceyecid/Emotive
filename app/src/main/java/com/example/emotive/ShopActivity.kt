package com.example.emotive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_mood_report.*
import kotlinx.android.synthetic.main.activity_mood_report.mrPrevImage
import kotlinx.android.synthetic.main.activity_shop.*
import com.google.gson.reflect.TypeToken

class ShopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        var jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "store.json")
        var gson : Gson = Gson()

        //val jsonFileString = gson.toJson( "store.json" )
        //var list = arrayListOf<User>()

        var userList = gson.fromJson(jsonFileString, Array<Item>::class.java).asList()

        /*
        button.setOnClickListener {
            //textView.text= jsonFileString
            textView.text= userList.get(0).name
        }
         */
        petalTextView.text = userList.get(0).path
        //petalTextView.text = intent.getStringExtra("petalNum")

        mrPrevImage.setOnClickListener {
            finish()
        }
    }
}