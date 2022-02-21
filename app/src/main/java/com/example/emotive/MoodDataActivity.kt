package com.example.emotive

import MoodCard
import MoodCardContainerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_mood_data.*


class MoodDataActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_data)

        viewDate.text = "Today"

        val samples = loadSampleData()
        moodCardContainer.layoutManager = LinearLayoutManager(this)
        moodCardContainer.adapter = MoodCardContainerAdapter(samples)
    }

    // create a list of sample mood data
    private fun loadSampleData(): ArrayList<MoodCard> {
        val moodList: ArrayList<MoodCard> = ArrayList<MoodCard>()
        moodList.add( MoodCard(1, "I get a offer from dream studio it is my lucky day") )
        moodList.add( MoodCard(1, "There is a blue bird outside the window") )
        moodList.add( MoodCard(3, "Mom just called me she invite me to dinner tonight. Honestly I am not interesting in that and I do not want to see Edward it is awkward." ) )

        return moodList
    }
}