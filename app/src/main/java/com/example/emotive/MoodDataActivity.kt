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
    private lateinit var moods : List<Mood>

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_data)

        // initialize views
        backButton.setOnClickListener{ goBack() }
        viewDate.text = "Today"
        calendarButton.setOnClickListener{ pickDate() }
        moodRecyclerView.layoutManager = LinearLayoutManager( this )

//        // load sample data into DB
//        CoroutineScope( Dispatchers.IO ).launch {
//            loadSampleData()
//        }

        // database interaction
        moodViewModel = ViewModelProvider( this, MoodViewModelFactory( this.application ) ).get( MoodViewModel::class.java )
        moodViewModel.getMoodByTime( getDayStart( cal ).timeInMillis, getDayEnd( cal ).timeInMillis )
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
        moodViewModel.getMoodByTime( getDayStart( cal ).timeInMillis, getDayEnd( cal ).timeInMillis )

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


    /********** Utilities **********/


    // get start time of the day (00:00:00.000)
    private fun getDayStart( day : Calendar ) : Calendar
    {
        val start = day.clone() as Calendar
        start.set( Calendar.HOUR_OF_DAY, 0 )
        start.set( Calendar.MINUTE, 0 )
        start.set( Calendar.SECOND, 0 )
        start.set( Calendar.MILLISECOND, 0 )

        return start
    }

    // get end time of the day (23:59:59:999)
    private fun getDayEnd( day : Calendar ) : Calendar
    {
        val end = day.clone() as Calendar
        end.set( Calendar.HOUR_OF_DAY, 23 )
        end.set( Calendar.MINUTE, 59 )
        end.set( Calendar.SECOND, 59 )
        end.set( Calendar.MILLISECOND, 999 )

        return end
    }

    // create a list of sample mood data
    private suspend fun loadSampleData()
    {
        val db = AppDatabase.getDatabase( this ).appDao()
        var time : Calendar

        // 2021-3-23
        time = Calendar.getInstance()
        time.set( 2021, 2, 23 )
        db.insertMood( Mood( time, 1, "excited\n" + time.time.toString(), null ) )
        time = Calendar.getInstance()
        time.set( 2021, 2, 23 )
        db.insertMood( Mood( time, 3, "meh\n" + time.time.toString(), null ) )
        time = Calendar.getInstance()
        time.set( 2021, 2, 23 )
        db.insertMood( Mood( time, 5, "bad day\n" + time.time.toString(), null ) )

        // 2022-3-23
        time = Calendar.getInstance()
        time.set( 2022, 2, 23 )
        db.insertMood( Mood( time, 1, "excited\n" + time.time.toString(), null ) )
        time = Calendar.getInstance()
        time.set( 2022, 2, 23 )
        db.insertMood( Mood( time, 2, "happy\n" + time.time.toString(), null ) )
        time = Calendar.getInstance()
        time.set( 2022, 2, 23 )
        db.insertMood( Mood( time, 3, "meh\n" + time.time.toString(), null ) )

        // 2022-3-25
        time = Calendar.getInstance()
        time.set( 2022, 2, 25 )
        db.insertMood( Mood( time, 3, "meh\n" + time.time.toString(), null ) )
        time = Calendar.getInstance()
        time.set( 2022, 2, 25 )
        db.insertMood( Mood( time, 4, "sad\n" + time.time.toString(), null ) )
        time = Calendar.getInstance()
        time.set( 2022, 2, 25 )
        db.insertMood( Mood( time, 5, "cry\n" + time.time.toString(), null ) )

        // 2022-3-26
        time = Calendar.getInstance()
        time.set( 2022, 2, 26 )
        db.insertMood( Mood( time, 2, "joy\n" + time.time.toString(), null ) )
        time = Calendar.getInstance()
        time.set( 2022, 2, 26 )
        db.insertMood( Mood( time, 3, "meh\n" + time.time.toString(), null ) )
        time = Calendar.getInstance()
        time.set( 2022, 2, 26 )
        db.insertMood( Mood( time, 5, "cry\n" + time.time.toString(), null ) )
    }
}