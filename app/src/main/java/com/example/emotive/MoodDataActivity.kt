package com.example.emotive

import MoodCard
import MoodCardContainerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_mood_data.*
import java.util.*
import kotlin.collections.ArrayList


class MoodDataActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener
{
    // date selection
    private val cal = Calendar.getInstance()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_data)

        // initialize dateBar
        backButton.setOnClickListener{ goBack() }
        viewDate.text = "Today"
        calendarButton.setOnClickListener{ pickDate() }


        // load recycler view of mood cards
        val samples = loadSampleData()
        moodCardContainer.layoutManager = LinearLayoutManager(this)
        moodCardContainer.adapter = MoodCardContainerAdapter(samples)
    }

    // create a list of sample mood data
    private fun loadSampleData(): ArrayList<MoodCard> {
        val moodList: ArrayList<MoodCard> = ArrayList<MoodCard>()
        moodList.add( MoodCard(1, "I get a offer from dream studio it is my lucky day" ) )
        moodList.add( MoodCard(1, "There is a blue bird outside the window" ) )
        moodList.add( MoodCard(3, "Mom just called me she invite me to dinner tonight. Honestly I am not interesting in that and I do not want to see Edward it is awkward." ) )

        return moodList
    }


    /********** event listeners **********/

    // goes back to previous page
    private fun goBack()
    {
        super.onBackPressed()
    }

    // ask for date selection
    private fun pickDate()
    {
        DatePickerDialog( this, this, cal.get( Calendar.YEAR ), cal.get( Calendar.MONTH ), cal.get( Calendar.DATE ) ).show()
    }

    // set viewDate to the selected date
    override fun onDateSet( view: DatePicker?, year: Int, month: Int, day: Int )
    {
        cal.set( year, month, day )
        viewDate.text = getString( R.string.date, year, month, day )
    }
}