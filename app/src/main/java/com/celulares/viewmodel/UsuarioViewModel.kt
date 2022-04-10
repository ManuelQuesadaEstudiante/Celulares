package com.celulares.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.celulares.data.UsuarioDAO
import com.celulares.model.Usuario
import com.celulares.repository.UsuarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsuarioViewModel(application: Application): AndroidViewModel(application) {

    private var usuarioRepository: UsuarioRepository = UsuarioRepository(UsuarioDAO())

    fun getUsuario(idUsuario: String): Usuario?{

        return usuarioRepository.getUsuario(idUsuario)

    }

    fun registrarUsuario(nuevoUsuario: Usuario){

        viewModelScope.launch(Dispatchers.IO) { usuarioRepository.registrarUsuario(nuevoUsuario) }

    }

}