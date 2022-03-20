package com.example.emotive

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AppDao
{
    @Insert
    suspend fun insert( mood : Mood)

    @Query( "SELECT * FROM Mood" )
    fun getAllMoods() : LiveData<List<Mood>>
}
