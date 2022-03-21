package com.example.emotive

import android.media.Image
import androidx.room.*
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

// this class is an abstract representation of mood data

/*
@Entity (tableName = "users_table")
data class User (@ColumnInfo (name ="username") var username: String,
                 @ColumnInfo (name = "email") var email: String)
{
    @PrimaryKey(autoGenerate = true) var uid: Int = 0
}

 */

@Entity( tableName = "Mood" )
data class Mood(
    @ColumnInfo (name ="value") val value: Int,
    @ColumnInfo (name = "text") var text: String?,
    @Ignore var imageList: ArrayList<Image>?
    ): Serializable
{
    /********** alternative constructors **********/

    constructor( value: Int, text: String? ): this( value, text, null )
    @Ignore constructor( value: Int ): this( value, null, null )

    /********** additional attributes **********/

    // timestamp when initialized
    @PrimaryKey
    @TypeConverters( Converter::class )
    @ColumnInfo (name = "time") var time: Calendar = Calendar.getInstance()

    // the drawable resource index
    @Ignore
    val resource: Int = when( value )
    {
        1 -> R.drawable.tier01
        2 -> R.drawable.tier02
        3 -> R.drawable.tier03
        4 -> R.drawable.tier04
        5 -> R.drawable.tier05
        else -> -1
    }
}
