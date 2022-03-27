package com.example.emotive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_mood_report.*
import kotlinx.android.synthetic.main.activity_mood_report.mrPrevImage
import kotlinx.android.synthetic.main.activity_shop.*
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_data_shop.*
import kotlinx.android.synthetic.main.activity_shop.petalTextView

class ShopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_shop)
        setContentView(R.layout.activity_data_shop)

        var jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "store.json")
        var gson : Gson = Gson()

        //val jsonFileString = gson.toJson( "store.json" )
        //var list = arrayListOf<User>()

        var userList = gson.fromJson(jsonFileString, Array<Item>::class.java).asList()

        //NEW

        val recycler = findViewById<RecyclerView>(R.id.itemRecyclerView)

        //setting recycler to horizontal scroll
        itemRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val samples = loadSampleData()

        //itemRecyclerView.layoutManager = LinearLayoutManager(this)
        itemRecyclerView.adapter = ItemRecyclerViewAdapter(samples)

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
    private fun loadSampleData(): ArrayList<Item> {
        val moodList: ArrayList<Item> = ArrayList<Item>()
        moodList.add( Item(0, 20,"@drawable/item_button", 0 ) )
        moodList.add(  Item(0, 20,"@drawable/item_button", 1 ) )
        moodList.add(  Item(0, 20,"@drawable/item_button", 2 )  )

        return moodList
    }
}