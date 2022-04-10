package com.celulares.data

import com.celulares.model.Usuario

interface ResultListener {

    fun onResult(usuario: Usuario)
    fun onError(error: Throwable)

}