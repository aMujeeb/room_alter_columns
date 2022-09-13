package com.example.roomexample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomexample.data.StudentRepository

class MainViewModelFactory(private val mRepo: StudentRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(mRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}