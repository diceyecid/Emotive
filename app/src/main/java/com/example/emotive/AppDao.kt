package com.example.emotive

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AppDao
{
    /********** Mood **********/


    @Insert
    suspend fun insertMood( mood : Mood )

    @Update
    suspend fun updateMood( mood: Mood )

    @Query( "SELECT * FROM Mood" )
    fun getAllMoods() : LiveData<List<Mood>>

    @Query( "SELECT * FROM Mood WHERE time BETWEEN :start AND :end ORDER BY time DESC" )
    fun getMoodByTime( start : Long, end : Long ) : List<Mood>


    /********** Reward **********/

    @Insert
    suspend fun insertReward( reward : Reward )

    @Query( "SELECT * FROM Reward" )
    fun getAllRewards() : LiveData<List<Reward>>

    @Delete
    fun deleteReward( reward : Reward )
}
