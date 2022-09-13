package com.example.roomexample

import android.app.Application
import com.example.roomexample.data.AppDatabase
import com.example.roomexample.data.StudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class StudentsRepoApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    val mStudentDataBase by lazy {
        AppDatabase.getDatabase(this, applicationScope) //scope passed to do few operations on start
    }

    val mStudentRepo by lazy {
        StudentRepository(mStudentDataBase.studentDao())
    }
}