package com.celulares.repository

import com.celulares.data.UsuarioDAO
import com.celulares.model.Usuario

class UsuarioRepository(private var usuarioDao: UsuarioDAO) {

    fun getUsuario(idUsuario: String): Usuario?{

        return usuarioDao.getUsuario(idUsuario)

    }

    suspend fun registrarUsuario(nuevoUsuario: Usuario){

        usuarioDao.registrarUsuario(nuevoUsuario)

    }

}