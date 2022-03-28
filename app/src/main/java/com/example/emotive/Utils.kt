package com.example.emotive

import android.content.Context
import android.view.Window
import android.view.WindowManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
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


    fun arrayToString(arr: ArrayList<String> ): String{
        var myString = StringBuilder()
        for (item in arr)
        {
            myString.append(item).append(",")
        }
        return myString.toString()
    }

    fun stringToArray(text: String ): ArrayList<String>
    {
        var arr = text.split(",").toTypedArray()
        val list = arrayListOf<String>()
        //val builder = StringBuilder()
        for (item in arr)
        {
            var newLine = item
            //builder.append(item)
            //builder.append(item)
            //item.indexOf("D")
            //var newLine=StringBuffer(item).insert(5, "\n").toString();
            //var newLine = StringBuilder(item).insert(-5, "\n").toString()
            //item.indexOf("[")
            //builder.toString()
            list.add(item)

        }
        if(list.get(list.count()-1)==""){
            list.removeAt(list.count()-1)
        }
        return list
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


    /********** app config **********/


    fun hideSystemBars( window : Window)
    {
        val windowInsetsController = ViewCompat.getWindowInsetsController( window.decorView ) ?: return
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide( WindowInsetsCompat.Type.systemBars() )
    }

}