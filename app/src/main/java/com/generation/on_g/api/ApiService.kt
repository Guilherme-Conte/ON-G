package com.generation.on_g.api

import com.generation.on_g.modelo.Categoria
import com.generation.on_g.modelo.Postagem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    //Requisição das categorias - Lista de categorias
    @GET("temas")
    suspend fun listCategoria (): Response<List<Categoria>>

    //Requisições das Postagens
    @GET("postagens")
    suspend fun listPostagem(): Response<List<Postagem>>

    //Adicionar Tarefa
    @POST("postagens")
    suspend fun addPostagem(
        @Body postagem: Postagem
    ): Response<Postagem>
}