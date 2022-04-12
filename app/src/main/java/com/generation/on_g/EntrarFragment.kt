package com.generation.on_g

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.generation.on_g.databinding.FragmentEntrarBinding
import com.generation.on_g.databinding.FragmentRegistroBinding
import com.google.firebase.auth.FirebaseAuth

class EntrarFragment : Fragment() {

    private lateinit var binding: FragmentEntrarBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEntrarBinding.inflate(layoutInflater, container, false)

        val intent = Intent(context, MainActivity::class.java)

        binding.ButtonEntrar.setOnClickListener {
            val email = binding.editEmailE.text.toString()
            val senha = binding.editSenhaE.text.toString()

            if (email.isEmpty() || senha.isEmpty()){
                Toast.makeText(context, "Preencha os campos corretamente!",
                    Toast.LENGTH_LONG).show()

            }else {
                auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener {
                    if (it.isSuccessful){
                        startActivity(intent)
                    }

                }.addOnFailureListener {
                    Toast.makeText(context, "Erro ao fazer o login do usuário!",
                        Toast.LENGTH_LONG).show()
                }


               }

        }

        binding.ButtonRegistrarE.setOnClickListener {
            findNavController().navigate(R.id.action_entrarFragment_to_registroFragment)
        }


        return binding.root



    }
    override fun onStart(){
        super.onStart()
        val intent = Intent(context, MainActivity::class.java)
        val usuarioAtual = FirebaseAuth.getInstance().currentUser

        if (usuarioAtual != null){
           startActivity(intent)
        }
    }

}