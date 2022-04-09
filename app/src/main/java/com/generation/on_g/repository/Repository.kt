package com.generation.on_g.repository

import com.generation.on_g.api.RetrofitInstance
import com.generation.on_g.modelo.Categoria
import com.generation.on_g.modelo.Postagem
import retrofit2.Response

class Repository {

    suspend fun listCategoria (): Response<List<Categoria>>{
        return RetrofitInstance.api.listCategoria()
    }
    suspend fun addPostagem(postagem: Postagem): Response<Postagem>{
        return RetrofitInstance.api.addPostagem(postagem)
    }
    suspend fun listPostagem(): Response<List<Postagem>>{
        return RetrofitInstance.api.listPostagem()
    }
}