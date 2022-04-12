package com.generation.on_g

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.generation.on_g.databinding.FragmentFormularioPostBinding
import com.generation.on_g.modelo.Categoria
import com.generation.on_g.modelo.Postagem

import com.generation.on_g.mvvm.MainViewModel
import java.time.LocalDate

class FormularioPostFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentFormularioPostBinding

    private var categoriaSelecionada = 0L

    private var postagemSelecionada: Postagem? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFormularioPostBinding.inflate(
            layoutInflater, container, false
        )

        carregarDados()

        mainViewModel.listCategoria()
        mainViewModel.myCategoriaResponse.observe(viewLifecycleOwner){
                response -> Log.d("Requisição", response.body().toString())
                spinnerCategoria(response.body())
        }

        binding.buttonPublicar.setOnClickListener {
            inserirNoBanco()
        }

        return binding.root
    }
    fun spinnerCategoria(categorias: List<Categoria>?) {

        if (categorias != null) {
            binding.spinnerCategoria.adapter = ArrayAdapter(
                requireContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                categorias
            )
            binding.spinnerCategoria.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        p0: AdapterView<*>?, p1: View?, p2: Int,
                        p3: Long
                    ) {
                        val categoriaSelecionadaFun = binding
                            .spinnerCategoria.selectedItem as Categoria

                        categoriaSelecionada = categoriaSelecionadaFun.id
                    }

                    override fun onNothingSelected(
                        p0: AdapterView<*>?
                    ) {
                        TODO("Not yet implemented")
                    }

                }
        }
    }
    fun validaCampos(
        titulo: String, desc: String, link: String
        //localizacao: String
    ): Boolean {

        return !(
                (titulo == "" || titulo.length < 3 || titulo.length > 40) ||
                        (desc == "" || desc.length < 5 || desc.length > 500) ||
                        (link == "" || link.length < 3 || link.length > 300)
                        //(localizacao == "" || localizacao.length < 5 || localizacao.length > 25)
                )
    }
    fun inserirNoBanco() {

        val titulo = binding.editTitulo.text.toString()
        val desc = binding.editTextDescri.text.toString()
        val link = binding.editTextLink.text.toString()
        val autor = binding.editAutor.text.toString()
        val dataHora = LocalDate.now().toString()
        val categoria = Categoria(categoriaSelecionada, null, null)

        if (validaCampos(titulo, desc, link)) {
            if(postagemSelecionada == null) {
                val postagem = Postagem(0,
                    titulo, desc, link, dataHora, autor, categoria)

                mainViewModel.addPostagem(postagem)
            } else{
                val postagem = Postagem(
                    postagemSelecionada?.id!!,
                    titulo, desc, link, dataHora, autor, categoria
                )
                mainViewModel.updatePostagem(postagem)
            }
            Toast.makeText(context, "Postagem Salva", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_formularioPostFragment_to_postagemFragment)

        } else {
            Toast.makeText(context, "Preencha os campos corretamente!", Toast.LENGTH_LONG).show()

        }
    }


    private fun carregarDados(){
        postagemSelecionada = mainViewModel.postagemSelecionada
        if (postagemSelecionada != null){
            binding.editTitulo.setText(postagemSelecionada?.titulo)
            binding.editTextDescri.setText(postagemSelecionada?.descricao)
            binding.editTextLink.setText(postagemSelecionada?.imagem)
            binding.editAutor.setText(postagemSelecionada?.autor)
        } else {
            binding.editTitulo.text = null
            binding.editTextDescri.text = null
            binding.editTextLink.text = null
            binding.editAutor.text = null
        }
    }
}



