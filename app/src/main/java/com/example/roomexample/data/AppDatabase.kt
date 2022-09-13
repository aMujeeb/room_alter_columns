package com.example.roomexample.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomexample.dao.StudentDao
import com.example.roomexample.models.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@Database(
    entities = [Student::class],
    version = 1 ,
    exportSchema = false
) //true to Export schema used to version controlling
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao

    private class StudentDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            mInstance?.let { appDatabase ->
                scope.launch {
                    var studentDao = appDatabase.studentDao()
                    /*studentDao.deleteAll()

                    val student1 = Student(UUID.randomUUID().toString(), "Aji", 23)
                    studentDao.insertStudent(student1)
                    val student2 = Student(UUID.randomUUID().toString(), "Ali", 25)
                    studentDao.insertStudent(student2)
                    val student3 = Student(UUID.randomUUID().toString(), "Axi", 24)
                    studentDao.insertStudent(student3)*/
                }
            }
        }
    }

    companion object {

        @Volatile
        private var mInstance: AppDatabase? = null

        //When perfomimg migration.. Non Null in query and Data class both should be applied
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //Create New Table
                database.execSQL(
                    "CREATE TABLE students_new (st_id TEXT NOT NULL, st_name TEXT, age INTEGER NOT NULL, city TEXT NOT NULL, PRIMARY KEY(st_id))")

                //Replace null to empty string
                database.execSQL(
                    "UPDATE student_table SET city = '_' WHERE city is null")

                //Copy data
                database.execSQL(
                    "INSERT INTO students_new (st_id, st_name, age, city) SELECT st_id, st_name, age, city FROM student_table")

                // Remove the old table
                database.execSQL("DROP TABLE student_table")

                // Change the table name to the correct one
                database.execSQL("ALTER TABLE students_new RENAME TO student_table")
            }
        }

        public fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return mInstance ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(context, AppDatabase::class.java, "test_db")
                        .addCallback(StudentDatabaseCallback(scope))
                        .addMigrations(MIGRATION_1_2)
                        .build()
                mInstance = instance
                return instance
            }
        }
    }
}