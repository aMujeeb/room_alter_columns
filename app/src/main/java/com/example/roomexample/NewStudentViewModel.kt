package com.example.roomexample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomexample.data.StudentRepository
import com.example.roomexample.models.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class NewStudentViewModel(private val mRepo: StudentRepository) : ViewModel() {

    private var _insertStudent = MutableLiveData<Int>()
    val mStudentInserted: MutableLiveData<Int> = _insertStudent

    private var mStudent = Student(st_id = UUID.randomUUID().toString(), st_name= "", age = 0, city = null)
    fun setStudentName(name: String) {
        if (name.isNotEmpty()) mStudent.st_name = name
    }

    fun setStudentAge(age: String) {
        if (age.isNotEmpty()) mStudent.age = age.toInt()
    }

    fun saveStudent() {
        viewModelScope.launch(Dispatchers.IO) {
            mRepo.insertNewStudent(mStudent)
            _insertStudent.postValue(1)
        }
    }
}