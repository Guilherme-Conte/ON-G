package com.generation.on_g.mvvm

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.generation.on_g.modelo.Categoria
import com.generation.on_g.modelo.Postagem
import com.generation.on_g.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
        val repository: Repository
        ): ViewModel() {

    var postagemSelecionada: Postagem? = null

    private var _myCategoriaResponse =
        MutableLiveData<Response<List<Categoria>>>()
    val myCategoriaResponse: LiveData<Response<List<Categoria>>> =
        _myCategoriaResponse

    private val _myPostagemResponse =
        MutableLiveData<Response<List<Postagem>>>()
    val myPostagemResponse: LiveData<Response<List<Postagem>>> =
        _myPostagemResponse

    init {
        listCategoria()
    }

    fun listCategoria() {
        viewModelScope.launch {
            try {
                val response = repository.listCategoria()
                _myCategoriaResponse.value = response

            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }


    fun addPostagem(postagem: Postagem) {
        viewModelScope.launch {
            try {
                repository.addPostagem(postagem)
                postagem()
            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun postagem(){
        viewModelScope.launch{
            try{
                val response = repository.postagem()
                _myPostagemResponse.value = response
            }catch (e: Exception){
                Log.e("Requisicao",e.message.toString())
            }
        }
    }

    fun updatePostagem(postagem: Postagem){
        viewModelScope.launch {
            try {
                repository.updatePostagem(postagem)
                postagem()
            }catch (e: Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }
    fun deletePostagem(id: Long){
        viewModelScope.launch {
            try {
                repository.deletePostagem(id)
                postagem()
            }catch (e: Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }
}

