package com.example.emotive

import android.app.Application
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.os.Handler
import android.util.DisplayMetrics
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.alert_reward_gain.*
import java.io.IOException
import java.io.InputStream
import java.util.*

class MainActivity : AppCompatActivity() {

    // private lateinit var userData: UserData
    //var obj: JSONObject = JSONObject(loadJSONFromAsset())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Utils.hideSystemBars( window )

        var userData = UserData.getInstance( this )
        //userData.setPetals(50)
        //userData.lockAllItems()
        Log.d("unlock",userData.unlockedItems.size.toString())
        if(userData.unlockedItems.size==0){
            userData.unlockItem(Item(1,30, "@drawable/ava_fox", 3 ))
            userData.avatar=Item(1,30, "@drawable/ava_fox", 3 )
            userData.background=Item(-1,0, "@drawable/empty_icon", -1 )
            userData.decoration=Item(-1,0, "@drawable/empty_icon", -1 )
            intent = Intent (this,
                IntroActivity::class.java)
            startActivity( intent )
        }






        var bgDrawableId: Int = this.getResources().getIdentifier(
            userData.background!!.path,
            "drawable",
            this.getPackageName())
        backgroundImage.setImageResource(bgDrawableId)
        var avDrawableId: Int = this.getResources().getIdentifier(
            userData.avatar!!.path,
            "drawable",
            this.getPackageName())
        avatarImage.setImageResource(avDrawableId)
        var dcDrawableId: Int = this.getResources().getIdentifier(
            userData.decoration!!.path,
            "drawable",
            this.getPackageName())
        decorationImage.setImageResource(dcDrawableId)



        // get petal amount
        userData.liveBasket.observe( this) {
            petalText.text = it.toString()
        }



        /********** Mini Interactivity **********/



        val inactiveImageArr = arrayListOf(
            imageView3,
            imageView4,
            imageView5,
            imageView6,
            imageView7,
        )
        val activeImageArr = arrayListOf<ImageView>()

        val period = 3600000    // an hour in milliseconds

        // calculate how many petal should be generated for the inactive time
        while( Calendar.getInstance().timeInMillis - userData.lastIntensive > period )
        {
            if( userData.uncollected >= inactiveImageArr.size )
            {
                userData.lastIntensive = Calendar.getInstance().timeInMillis
            }
            else
            {
                userData.lastIntensive += period
                userData.uncollected++
            }
        }

        // generate petals on screen
        for( i in 0 until userData.uncollected )
        {
            if( inactiveImageArr.size > 0 )
            {
                val targetImageView = inactiveImageArr[0]
                targetImageView.setImageResource(R.drawable.petal)

                activeImageArr.add(targetImageView)
                inactiveImageArr.removeAt(0)
                targetImageView.setOnClickListener {
                    targetImageView.setImageResource(R.drawable.imageempty)
                    inactiveImageArr.add(targetImageView)
                    val targetIndex = activeImageArr.indexOf(targetImageView)
                    if (targetIndex >= 0)
                    {
                        activeImageArr.removeAt(targetIndex)
                        userData.addPetals((1 until 5).random())
                        userData.uncollected--
                    }
                }
            }
        }



        /********** navigation buttons ***********/



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
            customdialog.setContentView(R.layout.alert_reward_gain)
            customdialog.show()

            var rewardViewModel : RewardViewModel =
                ViewModelProvider(this, RewardViewModelFactory(this.application)).get( RewardViewModel::class.java )

            // rewardViewModel.insertReward( Reward( "test", 10 ) )

            //TODO THIS
            rewardViewModel.allRewards.observe( this, Observer{
                customdialog.rewardRecyclerView.layoutManager = LinearLayoutManager( this )
                customdialog.rewardRecyclerView.adapter = RewardRecyclerViewAdapter( it, rewardViewModel )
            } )
        }



    }

}


