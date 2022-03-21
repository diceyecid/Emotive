package com.example.emotive

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AppDao
{
    @Insert
    suspend fun insert( mood : Mood)

    @Query( "SELECT * FROM Mood" )
    fun getAllMoods() : LiveData<List<Mood>>
    /*
    @Query("UPDATE orders SET order_amount = :amount, price = :price WHERE order_id =:id")
    fun update(amount: Float?, price: Float?, id: Int)


     */
    /*
    @Query( "UPDATE Mood SET text = :text WHERE time = :time" )
    fun setMoodEntry( mood: Mood)
    */
    @Update
    fun updateMood( mood: Mood )
}
