package com.generation.on_g.adapter

import com.generation.on_g.modelo.Postagem

interface TaskItemClickListener {

    fun onTaskClicked(postagem: Postagem)
}