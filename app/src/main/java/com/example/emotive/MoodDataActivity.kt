package com.example.emotive

import Mood
import MoodContainerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_mood_data.*
import java.text.SimpleDateFormat
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
        moodCardContainer.adapter = MoodContainerAdapter(samples)
    }

    // create a list of sample mood data
    private fun loadSampleData(): ArrayList<Mood> {
        val moodList: ArrayList<Mood> = ArrayList<Mood>()
        moodList.add( Mood(1, "I get a offer from dream studio it is my lucky day" ) )
        moodList.add( Mood(2, "There is a blue bird outside the window" ) )
        moodList.add( Mood(3, "Mom just called me she invite me to dinner tonight. Honestly I am not interesting in that and I do not want to see Edward it is awkward." ) )

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
        val now = Calendar.getInstance()
        val temp = Calendar.getInstance()
        temp.set( year, month, day )

        // if the selected time is in the future
        if( now.before( temp ) )
            return
        // else set user calendar to the selected datetime
        else
            cal.set( year, month, day )

        val yest = Calendar.getInstance()
        yest.add( Calendar.DATE, -1 )

        // if today is selected
        if( now.get( Calendar.YEAR ) == year &&
            now.get( Calendar.MONTH ) == month &&
            now.get( Calendar.DATE ) == day
        )
        {
            viewDate.text = getString( R.string.today )
        }
        // if yesterday is selected
        else if( yest.get( Calendar.YEAR ) == year &&
                 yest.get( Calendar.MONTH ) == month &&
                 yest.get( Calendar.DATE ) == day
        )
        {
            viewDate.text = getString( R.string.yesterday )
        }
        // if this year is selected
        else if( now.get( Calendar.YEAR ) == year )
        {
            viewDate.text = getString( R.string.MM_dd, month+1, day)
        }
        else
        {
            viewDate.text = getString( R.string.yy_MM_dd, year, month+1, day )
        }
    }
}