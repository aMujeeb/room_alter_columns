package com.example.roomexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.roomexample.data.StudentRepository
import com.example.roomexample.models.Student
import kotlinx.coroutines.launch

class MainViewModel(private val mRepo: StudentRepository) : ViewModel() {

    val mAllStudents: LiveData<List<Student>> = mRepo.mAllStudents.asLiveData()
}