package com.example.emotive


import androidx.room.*
import java.io.Serializable
import java.util.*

@Entity( tableName = "Reward" )
data class Reward(
    @ColumnInfo( name = "type" ) var type: String
) : Serializable
{
    // constants for different rewards
    companion object
    {
        const val DAILY_TEXT = "daily text"
        const val DAILY_PHOTO = "daily photo"
    }

    // timestamp when initialized
    @PrimaryKey
    @TypeConverters( Converter::class )
    @ColumnInfo( name = "time" ) var time: Calendar = Calendar.getInstance()

    @ColumnInfo( name = "isNew" ) var isNew: Boolean = true

    @Ignore var text: String = when( type )
    {
        DAILY_TEXT -> "Daily reward:\nmood with text"
        DAILY_PHOTO -> "Daily reward:\nmood with photo"
        else -> ""
    }

    @Ignore var petal: Int = when( type )
    {
        DAILY_TEXT -> 5
        DAILY_PHOTO -> 5
        else -> 0
    }
}