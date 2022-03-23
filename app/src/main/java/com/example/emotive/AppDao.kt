package com.example.emotive

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface AppDao
{
    /********** Mood **********/


    @Insert
    suspend fun insertMood( mood : Mood )

    @Update
    fun updateMood( mood: Mood )

    @Query( "SELECT * FROM Mood" )
    fun getAllMoods() : LiveData<List<Mood>>

//    @Query( "SELECT * FROM Mood WHERE :mood.time" )
//    fun getMoodByTime( start : Calendar, end : Calendar ) : LiveData<List<Mood>>


    /********** Item **********/


    @Query( "SELECT * FROM Item" )
    fun getAllItems() : LiveData<List<Item>>


    /********** Reward **********/

    @Insert
    suspend fun insertReward( reward : Reward )

    @Query( "SELECT * FROM Reward" )
    fun getAllRewards() : LiveData<List<Reward>>

    @Delete
    fun deleteReward( reward : Reward )
}
