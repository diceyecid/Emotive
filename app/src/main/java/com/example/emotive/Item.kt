package com.example.emotive

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity( tableName = "Item" )
data class Item(@ColumnInfo(name ="type") var type: Int,
                @ColumnInfo(name = "price") var price: Int,
                @ColumnInfo(name = "path") var path: String
                ) : Serializable
{
    @PrimaryKey(autoGenerate = true) var uid: Long = 0
}

