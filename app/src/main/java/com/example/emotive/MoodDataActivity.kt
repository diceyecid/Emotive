package com.example.emotive

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_mood_data.*
import java.util.*


class MoodDataActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener
{
    // date selection
    private val cal = Calendar.getInstance()

    // database
    private lateinit var moodViewModel : MoodViewModel

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_data)

        // initialize views
        backButton.setOnClickListener{ goBack() }
        viewDate.text = getString( R.string.today )
        calendarButton.setOnClickListener{ pickDate() }
        moodRecyclerView.layoutManager = LinearLayoutManager( this )

        // database interaction
        moodViewModel = ViewModelProvider( this, MoodViewModelFactory( this.application ) )[MoodViewModel::class.java]
        moodViewModel.getMoodsByTime( Utils.getDayStart( cal ).timeInMillis, Utils.getDayEnd( cal ).timeInMillis )
        moodViewModel.moods.observe( this, {
            moodRecyclerView.adapter = MoodRecyclerViewAdapter( it )
        } )
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
        val selected = Calendar.getInstance()
        selected.set( year, month, day )

        // if the selected time is in the future
        if( now.before( selected ) )
            return

        // otherwise set user calendar to the selected datetime and retrieve moods from database
        cal.set( year, month, day )
        moodViewModel.getMoodsByTime( Utils.getDayStart( cal ).timeInMillis, Utils.getDayEnd( cal ).timeInMillis )

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