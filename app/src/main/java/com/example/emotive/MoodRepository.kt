package com.example.emotive

import android.content.Context
import androidx.lifecycle.MutableLiveData

class MoodRepository private constructor( app : Context )
{
    val moods : MutableLiveData<List<Mood>> = MutableLiveData<List<Mood>>()

    private val dao : AppDao = AppDatabase.getDatabase( app ).appDao()
    private var startTime : Long = 0
    private var endTime : Long = 0


    suspend fun insertMood(mood : Mood )
    {
        dao.insertMood( mood )
        refreshMoods()
    }

    suspend fun updateMood(mood : Mood )
    {
        dao.updateMood( mood )
        refreshMoods()
    }

    fun getMoodByTime( start : Long, end : Long )
    {
        startTime = start
        endTime = end
        refreshMoods()
    }

    private fun refreshMoods()
    {
        moods.postValue( dao.getMoodByTime( startTime, endTime ) )
    }


    /********** get instance **********/


    // singleton pattern to ensure only a single UserData exists
    companion object
    {
        private var instance : MoodRepository? = null

        fun getInstance( context : Context) : MoodRepository
        {
            if( instance == null )
            {
                synchronized( MoodRepository::class )
                {
                    instance = MoodRepository( context.applicationContext )
                }
            }

            return instance!!
        }
    }
}