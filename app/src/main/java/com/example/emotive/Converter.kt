package com.example.emotive

import androidx.room.TypeConverter
import java.util.*

class Converter
{
    @TypeConverter
    fun CalendarToMillis( cal : Calendar ) : Long
    {
       return cal.timeInMillis
    }

    @TypeConverter
    fun MillisToCalendar( millis : Long ) : Calendar
    {
        val cal = Calendar.getInstance()
        cal.timeInMillis = millis

        return cal
    }
}