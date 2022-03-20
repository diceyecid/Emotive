package com.example.emotive

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoodViewModel( app : Application ) : ViewModel()
{
    private val dao : AppDao = AppDatabase.getDatabase( app ).appDao()
    val allMoods : LiveData<List<Mood>> = dao.getAllMoods()

    fun insert( mood : Mood ) = viewModelScope.launch( Dispatchers.IO ){ dao.insert( mood ) }
}