package com.generation.on_g

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.generation.on_g.adapter.PostAdapter
import com.generation.on_g.databinding.FragmentPostagemBinding
import com.generation.on_g.mvvm.MainViewModel

class PostagemFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding : FragmentPostagemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainViewModel.listPostagem()

        binding = FragmentPostagemBinding.inflate(
                        layoutInflater, container,false
        )

         /*
        val view = inflater.inflate(R.layout.fragment_postagem, container, false)
        val listPostagem = mutableListOf(Postagem(
            "Doação de Roupas",
            "Rua dois, Zona Leste - SP",
            "Doações feitas online",
            "Vestimentas",
            "Campanha do Agasalho",
            "22-03-2022 / 09:00"

        ),Postagem(
            "Doação de Comida",
            "Rua Tres, Zona Sul - SP",
            "Doações feitas na igreja, Santuarios Mãe de Deus",
            "Alimenticia",
            "Campanha FomeZero",
            "26-03-2022 / 12:00"

        ),Postagem(
            "Itens Básicos",
            "Rua Quatro, Zona Norte - SP",
            "Coleta de doações na zona norte - Online",
            "Humanitário",
            "Projeto Mais Amor SP",
            "25-03-2022 / 09:00"

        ),Postagem(
            " +Ração e -MausTratos ",
            "Rua Cinco, Zona Oeste - SP",
            "Distribuição de ração para animais de rua e suporte veterinário",
            "Animais",
            "Projeto PetLove",
            "22-03-2022 / 07:00"
        ))

          */



        //val floatingAdd = view.findViewById<FloatingActionButton>(R.id.floatingAdd)
        val postAdapter = PostAdapter()
        binding.floatingAdd.setOnClickListener{
            findNavController().navigate(R.id.action_postagemFragment_to_formularioPostFragment)
        }
        mainViewModel.myPostagemResponse.observe(viewLifecycleOwner)
        { response -> if (response.body() != null) {
               postAdapter.attList(response.body()!!)
            }
            Log.d("requisição", response.body().toString())
        }

        val recyclerPost = binding.recyclerPost


        binding.recyclerPost.adapter = postAdapter
        binding.recyclerPost.setHasFixedSize(true)
        //postAdapter.attList(listPostagem)
        binding.recyclerPost.layoutManager = LinearLayoutManager(context)


        return binding.root
    }
}