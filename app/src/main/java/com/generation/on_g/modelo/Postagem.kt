package com.generation.on_g.modelo

import android.widget.ImageView

data class Postagem(
    val id : Long,
    var titulo : String,
    var descricao : String,
    var imagem : String,
    var datahora : String,
    var autor : String,
    var tema : Categoria
    ){
}