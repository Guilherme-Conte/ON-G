package com.generation.on_g

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.generation.on_g.adapter.PostAdapter
import com.generation.on_g.adapter.TaskItemClickListener
import com.generation.on_g.databinding.FragmentPostagemBinding
import com.generation.on_g.modelo.Postagem
import com.generation.on_g.mvvm.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth

class PostagemFragment : Fragment(), TaskItemClickListener{

    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding : FragmentPostagemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainViewModel.postagem()

        binding = FragmentPostagemBinding.inflate(layoutInflater, container,false)

        val intentMaps = Intent(context, MapsMainActivity::class.java)
        val voltarTelaLogin = Intent(context, RegistroActivity::class.java)

        binding.floatingAdd.setOnClickListener {
            mainViewModel.postagemSelecionada = null
            findNavController().navigate(R.id.action_postagemFragment_to_formularioPostFragment)
        }

       binding.bMaps.setOnClickListener {
           startActivity(intentMaps)
       }

        binding.imageButtonDeslog.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(voltarTelaLogin)

        }

        val postAdapter = PostAdapter(context,this, mainViewModel)

        binding.recyclerPost.adapter = postAdapter
        binding.recyclerPost.setHasFixedSize(true)
        binding.recyclerPost.layoutManager = LinearLayoutManager(context)

        mainViewModel.myPostagemResponse.observe(viewLifecycleOwner) {

                response -> if (response != null) {
                postAdapter.attList(response.body()!!)
            }
        }



        return binding.root
    }

    override fun onTaskClicked(postagem: Postagem) {
        mainViewModel.postagemSelecionada = postagem
        findNavController().navigate(R.id.action_postagemFragment_to_formularioPostFragment)
    }
}