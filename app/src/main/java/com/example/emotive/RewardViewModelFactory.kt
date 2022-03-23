package com.example.emotive

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RewardViewModelFactory( private val app : Application ) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T> ): T
    {
        return RewardViewModel( app ) as T
    }
}
