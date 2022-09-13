package com.example.roomexample.data

import androidx.annotation.WorkerThread
import com.example.roomexample.dao.StudentDao
import com.example.roomexample.models.Student
import kotlinx.coroutines.flow.Flow

class StudentRepository(private val mStudentDao: StudentDao) {

    val mAllStudents: Flow<List<Student>> = mStudentDao.selectAllStudents()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertNewStudent(student: Student) {
        mStudentDao.insertStudent(student)
    }
}