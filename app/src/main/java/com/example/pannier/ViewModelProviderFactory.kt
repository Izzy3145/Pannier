package com.example.pannier

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.RuntimeException
import javax.inject.Provider

const val TAG = "Cannot invoke method length() on null object"

class ViewModelProviderFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        TODO("implement function")
    }
}

