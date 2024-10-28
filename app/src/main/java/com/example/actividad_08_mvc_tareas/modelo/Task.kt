package com.example.actividad_08_mvc_tareas.modelo

data class Task(
    val id: Int,
    var title: String,
    var description: String,
    var completed: Boolean = false
)