package com.example.emotive

import android.net.Uri
import androidx.core.net.toUri
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

    @TypeConverter fun UriToString(uri: Uri): String
    {
        return uri.toString()
    }

    @TypeConverter fun StringToUri(string: String): Uri
    {
        return string.toUri()
    }
}