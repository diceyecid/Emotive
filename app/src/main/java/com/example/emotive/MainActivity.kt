package com.example.emotive

import android.app.Application
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.alert_reward_gain.*
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    // private lateinit var userData: UserData
    //var obj: JSONObject = JSONObject(loadJSONFromAsset())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var userData = UserData.getInstance( this )
        //userData.setPetals(50)
        //userData.lockAllItems()
        Log.d("unlock",userData.unlockedItems.size.toString())
        if(userData.unlockedItems.size==0){
            intent = Intent (this,
                IntroActivity::class.java)
            startActivity( intent )
            /*
            userData.unlockItem(Item(1,30, "@drawable/ava_fox", 3 ))
            userData.avatar=Item(1,30, "@drawable/ava_fox", 3 )
            userData.background=Item(-1,0, "@drawable/empty_icon", -1 )
            userData.decoration=Item(-1,0, "@drawable/empty_icon", -1 )
            */
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
        userData.liveBasket.observe( this, {
            petalText.text = it.toString()
        } )

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
            /*

            val mDialogView = LayoutInflater.from(this).inflate(R.layout.alert_reward_gain, null);
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
            val mAlertDialog = mBuilder.show()
            */
            val customdialog = Dialog(this )
            customdialog.setContentView(R.layout.alert_reward_gain)
            customdialog.show()

            val rewardViewModel : RewardViewModel =
                ViewModelProvider(this, RewardViewModelFactory(this.application)).get( RewardViewModel::class.java )

            // rewardViewModel.insertReward( Reward( "test", 10 ) )

            //TODO THIS
            rewardViewModel.allRewards.observe( this, Observer{
                customdialog.rewardRecyclerView.layoutManager = LinearLayoutManager( this )
                customdialog.rewardRecyclerView.adapter = RewardRecyclerViewAdapter( it, rewardViewModel )
            } )

            /*
            customdialog.textView1.setOnClickListener{
                customdialog.dismiss()
            }
            */

            //OLD
            /*
            val customdialog = Dialog(this )
            customdialog.setContentView(R.layout.activity_reward_gain)
            customdialog.show()

             */
        }



    }

}


