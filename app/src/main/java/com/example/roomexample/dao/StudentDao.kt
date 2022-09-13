package com.example.roomexample.dao

import androidx.room.*
import com.example.roomexample.models.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertStudent(vararg student: Student)

    @Delete
    fun removeUser(student: Student)

    @Query("SELECT * FROM student_table")
    fun selectAllStudents(): Flow<List<Student>>

    @Query("DELETE FROM student_table")
    suspend fun deleteAll()
}