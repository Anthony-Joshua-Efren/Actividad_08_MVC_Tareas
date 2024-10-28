package com.example.actividad_08_mvc_tareas.controlador

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad_08_mvc_tareas.R
import com.example.actividad_08_mvc_tareas.modelo.Task
import com.example.actividad_08_mvc_tareas.vista.AddTaskFragment
import com.example.actividad_08_mvc_tareas.vista.TaskAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var taskController: TaskController
    private val taskList = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recyclerViewTasks)
        recyclerView.layoutManager = LinearLayoutManager(this)

        taskAdapter = TaskAdapter(taskList) { task, action ->
            when (action) {
                "complete" -> taskController.markTaskAsCompleted(task)
                "delete" -> taskController.deleteTask(task)
            }
        }

        taskController = TaskController(taskList, taskAdapter)
        recyclerView.adapter = taskAdapter

        findViewById<Button>(R.id.buttonAddTask).setOnClickListener {
            openAddTaskFragment()
        }
    }

    private fun openAddTaskFragment() {
        val fragment = AddTaskFragment(taskController)
        fragment.show(supportFragmentManager, "AddTaskFragment")
    }
}