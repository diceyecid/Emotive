package com.example.emotive

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_mood_data.*
import kotlinx.android.synthetic.main.alert_reward_gain.*

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
            /*

            val mDialogView = LayoutInflater.from(this).inflate(R.layout.alert_reward_gain, null);
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
            val mAlertDialog = mBuilder.show()
            */
            val customdialog = Dialog(this )
            customdialog.setContentView(R.layout.alert_reward_gain)
            customdialog.show()

            var rewardViewModel : RewardViewModel =
                ViewModelProvider(this, RewardViewModelFactory(this.application)).get( RewardViewModel::class.java )

            //TODO THIS
            /*
            rewardViewModel.allRewards.observe( this, Observer{
                customdialog.rewardRecyclerView.layoutManager = LinearLayoutManager( this )
                customdialog.rewardRecyclerView.adapter = RewardRecyclerViewAdapter( it )
            } )
            */

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


