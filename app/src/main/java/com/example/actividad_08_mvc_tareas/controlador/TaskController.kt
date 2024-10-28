package com.example.actividad_08_mvc_tareas.controlador

import com.example.actividad_08_mvc_tareas.modelo.Task
import com.example.actividad_08_mvc_tareas.vista.TaskAdapter

class TaskController(
    private val taskList: MutableList<Task>,
    private val taskAdapter: TaskAdapter
) {
    private var nextId = 1

    fun addTask(title: String, description: String) {
        val newTask = Task(id = nextId++, title = title, description = description)
        taskList.add(newTask)
        taskAdapter.notifyDataSetChanged()
    }

    fun markTaskAsCompleted(task: Task) {
        task.completed = true
        taskAdapter.notifyDataSetChanged()
    }

    fun deleteTask(task: Task) {
        taskList.remove(task)
        taskAdapter.notifyDataSetChanged()
    }
}