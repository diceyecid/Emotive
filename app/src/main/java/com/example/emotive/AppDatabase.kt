package com.example.emotive

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database( entities = [ Mood::class, Item::class, Reward::class ], version = 1 )
abstract class AppDatabase : RoomDatabase()
{
    abstract fun appDao() : AppDao

    companion object
    {
        private var instance : AppDatabase? = null

        fun getDatabase( context : Context ) : AppDatabase
        {
            if( instance == null )
            {
                synchronized( AppDatabase::class )
                {
                    instance = Room.databaseBuilder( context.applicationContext, AppDatabase::class.java, "app-db" ).build()
                }
            }

            return instance!!
        }
    }
}