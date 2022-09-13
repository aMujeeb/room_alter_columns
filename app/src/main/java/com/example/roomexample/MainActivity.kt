package com.example.roomexample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.roomexample.data.AppDatabase
import com.example.roomexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mMainViewBinding: ActivityMainBinding
    private val newWordActivityRequestCode = 1

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                Toast.makeText(this@MainActivity, "Insert Success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, "Insert Error", Toast.LENGTH_SHORT).show()
            }
        }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as StudentsRepoApplication).mStudentRepo)
    }

    private val mStudentsAdapter by lazy {
        StudentListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMainViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mMainViewBinding.root)

        val lstStudents = mMainViewBinding.mLstStudents
        lstStudents.adapter = mStudentsAdapter
        lstStudents.layoutManager = LinearLayoutManager(this)

        mainViewModel.mAllStudents.observe(this) { students ->
            students?.let {
                mStudentsAdapter.submitList(it)
            }
        }

        mMainViewBinding.mFab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewStudentActivity::class.java)
            resultLauncher.launch(intent)
        }
    }
}