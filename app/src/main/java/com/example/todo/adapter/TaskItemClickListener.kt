package com.example.todo.adapter

import com.example.todo.model.Tarefa

interface TaskItemClickListener {

    fun onTaskClicked(tarefa: Tarefa)

}