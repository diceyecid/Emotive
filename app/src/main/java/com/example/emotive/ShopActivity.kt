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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_shop.petalTextView

class ShopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_shop)
        setContentView(R.layout.activity_data_shop)
        Utils.hideSystemBars( window )

        var jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "store.json")
        var gson : Gson = Gson()

        var user = UserData.getInstance(this)
        user.liveBasket.observe( this, {
            petalTextView.text = it.toString()
        } )
//        petalTextView.text = user.basket.toString()
        //val jsonFileString = gson.toJson( "store.json" )
        //var list = arrayListOf<User>()

        val dcSamples: ArrayList<Item> = ArrayList<Item>()
        val avSamples: ArrayList<Item> = ArrayList<Item>()
        val bgSamples: ArrayList<Item> = ArrayList<Item>()

        var userList = gson.fromJson(jsonFileString, Array<Item>::class.java).asList()
        //val length =userList.size()
        for (item in 0 until userList.size) {
            if(userList.get(item).type == 0){
                dcSamples.add( Item(0, userList[item].price, userList[item].path , userList[item].uid ) )
            }
            else if(userList.get(item).type == 1){
                avSamples.add( Item(1, userList[item].price, userList[item].path , userList[item].uid ) )
            }
            else if(userList.get(item).type == 2){
                bgSamples.add( Item(2, userList[item].price, userList[item].path , userList[item].uid ) )
            }
        }


        //NEW

        val dcRecycler = findViewById<RecyclerView>(R.id.dcRecyclerView)

        //setting recycler to horizontal scroll
        dcRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //val dcSamples = loadSampleData()

        //itemRecyclerView.layoutManager = LinearLayoutManager(this)
        dcRecyclerView.adapter = ItemRecyclerViewAdapter(dcSamples, applicationContext)

        //val avRecycler = findViewById<RecyclerView>(R.id.bgRecyclerView)

        //setting recycler to horizontal scroll
        avRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //val avSamples = loadSampleData2()

        //itemRecyclerView.layoutManager = LinearLayoutManager(this)
        avRecyclerView.adapter = ItemRecyclerViewAdapter(avSamples, applicationContext)

        val bgRecycler = findViewById<RecyclerView>(R.id.bgRecyclerView)

        //setting recycler to horizontal scroll
        bgRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //val bgSamples = loadSampleData3()

        //itemRecyclerView.layoutManager = LinearLayoutManager(this)
        bgRecyclerView.adapter = ItemRecyclerViewAdapter(bgSamples, applicationContext)

        /*
        button.setOnClickListener {
            //textView.text= jsonFileString
            textView.text= userList.get(0).name
        }
         */
        //petalTextView.text = userList.get(0).path
        //petalTextView.text = intent.getStringExtra("petalNum")

        mrPrevImage.setOnClickListener {
            finish()
        }
    }
    private fun loadSampleData(): ArrayList<Item> {
        val moodList: ArrayList<Item> = ArrayList<Item>()
        moodList.add( Item(0, 25,"@drawable/decor_butterfly", 0 ) )
        moodList.add(  Item(0, 22,"@drawable/decor_santa_hat", 1 ) )
        moodList.add(  Item(0, 23,"@drawable/decor_tennis", 2 )  )

        return moodList
    }

    private fun loadSampleData2(): ArrayList<Item> {
        val moodList: ArrayList<Item> = ArrayList<Item>()
        moodList.add( Item(1, 35,"@drawable/ava_fox", 3 ) )
        moodList.add(  Item(1, 32,"@drawable/ava_owl", 4 ) )
        moodList.add(  Item(1, 33,"@drawable/ava_red_panda", 5 )  )

        return moodList
    }

    private fun loadSampleData3(): ArrayList<Item> {
        val moodList: ArrayList<Item> = ArrayList<Item>()
        moodList.add( Item(2, 45,"@drawable/bg_forest", 6 ) )
        moodList.add(  Item(2, 42,"@drawable/bg_sunset_field", 7 ) )

        return moodList
    }
}