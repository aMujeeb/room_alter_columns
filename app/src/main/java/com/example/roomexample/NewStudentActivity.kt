package com.example.roomexample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.example.roomexample.databinding.ActivityNewStudentBinding
import com.example.roomexample.models.Student
import java.util.*

class NewStudentActivity : AppCompatActivity() {
    private lateinit var mAddNewStudentViewBinding: ActivityNewStudentBinding

    private val mNewStudentViewModel: NewStudentViewModel by viewModels {
        NewStudentViewModelFactory((application as StudentsRepoApplication).mStudentRepo)
    }
    val replyIntent = Intent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAddNewStudentViewBinding = ActivityNewStudentBinding.inflate(layoutInflater)
        setContentView(mAddNewStudentViewBinding.root)

        mAddNewStudentViewBinding.mBtnSave.setOnClickListener {
            if (TextUtils.isEmpty(mAddNewStudentViewBinding.mTxtStudentName.text) || TextUtils.isEmpty(
                    mAddNewStudentViewBinding.mTxtStudentAge.text
                )
            ) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
                finish()
            } else {
                mNewStudentViewModel.saveStudent()
            }
        }

        mAddNewStudentViewBinding.mTxtStudentName.doAfterTextChanged {
            mNewStudentViewModel.setStudentName(it.toString())
        }

        mAddNewStudentViewBinding.mTxtStudentAge.doAfterTextChanged {
            mNewStudentViewModel.setStudentAge(it.toString())
        }

        mNewStudentViewModel.mStudentInserted.observe(this) {
            setResult(Activity.RESULT_OK, replyIntent)
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.roomexample.REPLY"
    }
}