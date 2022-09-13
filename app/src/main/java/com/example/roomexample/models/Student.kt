package com.example.roomexample.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "student_table")
data class Student(@PrimaryKey var st_id: String, var st_name: String?, var age: Int, var city: String? = "")
//If Altering a Column to Non Null the attribute of the data class also be Non null

/*@Entity(tableName = "student_table")
data class Student(@PrimaryKey var st_id: String, var st_name: String?, var age: Int)*/
