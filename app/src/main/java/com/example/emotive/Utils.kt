package com.example.emotive

import android.content.Context
import java.io.IOException
import java.util.*

object Utils {
    fun getJsonFromAssets(context: Context, fileName: String?): String? {
        val jsonString: String
        jsonString = try {
            val `is` = context.assets.open(fileName!!)
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }


    /********** time related helper functions **********/


    // get start time of the day (00:00:00.000)
    fun getDayStart( day : Calendar) : Calendar
    {
        val start = day.clone() as Calendar
        start.set( Calendar.HOUR_OF_DAY, 0 )
        start.set( Calendar.MINUTE, 0 )
        start.set( Calendar.SECOND, 0 )
        start.set( Calendar.MILLISECOND, 0 )

        return start
    }

    // get end time of the day (23:59:59:999)
    fun getDayEnd( day : Calendar) : Calendar
    {
        val end = day.clone() as Calendar
        end.set( Calendar.HOUR_OF_DAY, 23 )
        end.set( Calendar.MINUTE, 59 )
        end.set( Calendar.SECOND, 59 )
        end.set( Calendar.MILLISECOND, 999 )

        return end
    }
}