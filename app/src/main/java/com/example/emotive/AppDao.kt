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

    @Query( "SELECT * FROM Mood WHERE date( time, 'unixepoch' ) = date( :date )" )
    fun getMoodByDate( date : String ) : LiveData<List<Mood>>


    /********** Reward **********/

    @Insert
    suspend fun insertReward( reward : Reward )

    @Query( "SELECT * FROM Reward" )
    fun getAllRewards() : LiveData<List<Reward>>

    @Delete
    fun deleteReward( reward : Reward )
}
