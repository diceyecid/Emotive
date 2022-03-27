package com.example.emotive

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoodViewModel( app : Application ) : ViewModel()
{
    private val repo : MoodRepository = MoodRepository.getInstance( app )
    val moods : MutableLiveData<List<Mood>> = repo.moods

    fun insertMood(mood : Mood ) = viewModelScope.launch( Dispatchers.IO )
    {
        repo.insertMood( mood )
    }

    fun updateMood(mood : Mood ) = viewModelScope.launch( Dispatchers.IO )
    {
        repo.updateMood( mood )
    }

    fun getMoodsByTime(start : Long, end : Long ) = viewModelScope.launch( Dispatchers.IO )
    {
        repo.getMoodsByTime( start, end )
    }
}