package com.celulares.ui.celular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.celulares.databinding.FragmentCelularBinding
import com.celulares.viewmodel.UsuarioViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CelularFragment : Fragment() {

    private var _binding: FragmentCelularBinding? = null
    private val binding get() = _binding!!

    private lateinit var usuarioViewModel: UsuarioViewModel

    private lateinit var autenticacion : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(CelularViewModel::class.java)

        FirebaseApp.initializeApp(requireContext())
        autenticacion = Firebase.auth

        usuarioViewModel = ViewModelProvider(this)[UsuarioViewModel::class.java]

        _binding = FragmentCelularBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.button.setOnClickListener {

            mostrarInformacionUsuario()

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun mostrarInformacionUsuario(){

        var usuario = usuarioViewModel.getUsuario(autenticacion.currentUser!!.uid)

        if(usuario!=null){

            Toast.makeText(requireContext(),usuario?.nombreUsuario,Toast.LENGTH_SHORT).show()

        } else{

            Toast.makeText(requireContext(),"F",Toast.LENGTH_SHORT).show()

        }

    }

}