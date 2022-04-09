package com.generation.on_g.modelo

data class Categoria (
    val id : Long,
    val descricao : String?,
    var postagem: List<Postagem>?
){
    override fun toString(): String {
        return descricao!!
    }
}