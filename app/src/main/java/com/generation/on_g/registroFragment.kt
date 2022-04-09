package com.generation.on_g

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.generation.on_g.databinding.FragmentRegistroBinding


class registroFragment : Fragment() {

    private lateinit var binding: FragmentRegistroBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistroBinding.inflate(layoutInflater, container, false)

        binding.ButtonRegistrar.setOnClickListener {
            val nome = binding.editNome.text.toString()
            val email = binding.editEmail.text.toString()
            val telefone = binding.editTelefone.text.toString()
            val senha = binding.editSenha.text.toString()
            val confirSenha = binding.editConfiSenha.text.toString()

            if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty()
                || senha.isEmpty() || confirSenha.isEmpty()){
                Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_LONG
                ).show()
            }else{
                findNavController().navigate(R.id.action_registroFragment_to_postagemFragment)
            }
        }
        return binding.root

    }
}