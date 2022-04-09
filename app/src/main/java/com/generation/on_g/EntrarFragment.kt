package com.generation.on_g

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.generation.on_g.databinding.FragmentEntrarBinding
import com.generation.on_g.databinding.FragmentRegistroBinding

class EntrarFragment : Fragment() {

    private lateinit var binding: FragmentEntrarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEntrarBinding.inflate(layoutInflater, container, false)

        binding.ButtonEntrar.setOnClickListener {
            val email = binding.editEmailE.text.toString()
            val senha = binding.editSenhaE.text.toString()

            if (email.isEmpty() || senha.isEmpty()){
                Toast.makeText(context, "Preencha os campos corretamente!",
                    Toast.LENGTH_LONG).show()
            }else{
                findNavController().navigate(R.id.action_entrarFragment2_to_postagemFragment)
            }
        }
        binding.ButtonRegistrarE.setOnClickListener {
            findNavController().navigate(R.id.action_entrarFragment2_to_registroFragment)
        }
        return binding.root
    }


}