package com.example.roomexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomexample.databinding.ItemStudentBinding
import com.example.roomexample.models.Student

class StudentListAdapter :
    ListAdapter<Student, StudentListAdapter.StudentViewHolder>(StudentsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val viewHolderViewBinding: ItemStudentBinding = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StudentViewHolder(viewHolderViewBinding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class StudentViewHolder(private val itemStudent: ItemStudentBinding) :
        RecyclerView.ViewHolder(itemStudent.root) {
        fun bind(student: Student) {
            itemStudent.mLblStudentName.text = student.st_name
            itemStudent.mLblStudentId.text = student.st_id
            itemStudent.mLblStudentAge.text = student.age.toString()
            itemStudent.mLblCity.text = student.city
        }
    }

    class StudentsComparator : DiffUtil.ItemCallback<Student>() {
        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem == newItem
        }
    }
}