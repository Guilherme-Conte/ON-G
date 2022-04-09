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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFormularioPostBinding.inflate(
            layoutInflater, container, false
        )

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

    fun validarCampos(
        titulo: String, desc: String, link: String,
         ): Boolean {

        return !(
                 (titulo == "" || titulo.length < 3 || titulo.length > 20) ||
                 (desc == "" || desc.length < 5 || desc.length > 300) ||
                 (link == "" || link.length < 3 || link.length > 100)
                )
    }
    fun inserirNoBanco() {

        val titulo = binding.editTitulo.text.toString()
        val desc = binding.editTextDescri.text.toString()
        val link = binding.editTextLink.text.toString()
        val autor = "usuário"
        val dataHora = LocalDate.now().toString()
        val categoria = Categoria(categoriaSelecionada, null, null)

        if (validarCampos(titulo, desc, link)) {
            val postagem = Postagem(0,
                titulo, desc, link, dataHora, autor, categoria)

            mainViewModel.addPostagem(postagem)
            Toast.makeText(context, "Postagem Salva", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_formularioPostFragment_to_postagemFragment)

        } else {
            Toast.makeText(context, "Preencha os campos corretamente!", Toast.LENGTH_LONG).show()

        }
    }
}



