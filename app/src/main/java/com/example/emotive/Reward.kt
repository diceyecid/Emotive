package com.example.emotive


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity( tableName = "Reward" )
data class Reward(@ColumnInfo(name ="text") var text: String,
                  @ColumnInfo(name = "petal") var petal: Int,
) : Serializable
{
    @PrimaryKey(autoGenerate = true) var uid: Long = 0
    @ColumnInfo(name = "isNew") var isNew: Boolean = true
}