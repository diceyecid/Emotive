package com.example.emotive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_mood_report.*
import kotlinx.android.synthetic.main.activity_mood_report.mrPrevImage
import kotlinx.android.synthetic.main.activity_shop.*
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

        val dcRecycler = findViewById<RecyclerView>(R.id.dcRecyclerView)

        //setting recycler to horizontal scroll
        dcRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val dcSamples = loadSampleData()

        //itemRecyclerView.layoutManager = LinearLayoutManager(this)
        dcRecyclerView.adapter = ItemRecyclerViewAdapter(dcSamples)

        val avRecycler = findViewById<RecyclerView>(R.id.bgRecyclerView)

        //setting recycler to horizontal scroll
        avRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val avSamples = loadSampleData2()

        //itemRecyclerView.layoutManager = LinearLayoutManager(this)
        avRecyclerView.adapter = ItemRecyclerViewAdapter(avSamples)

        val bgRecycler = findViewById<RecyclerView>(R.id.bgRecyclerView)

        //setting recycler to horizontal scroll
        bgRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val bgSamples = loadSampleData3()

        //itemRecyclerView.layoutManager = LinearLayoutManager(this)
        bgRecyclerView.adapter = ItemRecyclerViewAdapter(bgSamples)

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
        moodList.add( Item(0, 25,"@drawable/item_button", 0 ) )
        moodList.add(  Item(0, 22,"@drawable/item_button", 1 ) )
        moodList.add(  Item(0, 23,"@drawable/item_button", 2 )  )

        return moodList
    }

    private fun loadSampleData2(): ArrayList<Item> {
        val moodList: ArrayList<Item> = ArrayList<Item>()
        moodList.add( Item(1, 35,"@drawable/item_button", 3 ) )
        moodList.add(  Item(1, 32,"@drawable/item_button", 4 ) )
        moodList.add(  Item(1, 33,"@drawable/item_button", 5 )  )

        return moodList
    }

    private fun loadSampleData3(): ArrayList<Item> {
        val moodList: ArrayList<Item> = ArrayList<Item>()
        moodList.add( Item(2, 45,"@drawable/item_button", 6 ) )
        moodList.add(  Item(2, 42,"@drawable/item_button", 7 ) )
        moodList.add(  Item(2, 43,"@drawable/item_button", 8 )  )

        return moodList
    }
}