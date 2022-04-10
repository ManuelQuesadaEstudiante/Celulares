package com.celulares.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Usuario(

    var idUsuario: String,
    var nombreUsuario: String,
    var correoUsuario: String

): Parcelable {

    constructor():

            this("","","")

}
