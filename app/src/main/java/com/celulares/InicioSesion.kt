package com.celulares

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.celulares.databinding.ActivityInicioSesionBinding
import com.celulares.model.Usuario
import com.celulares.repository.UsuarioRepository
import com.celulares.viewmodel.UsuarioViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class InicioSesion : AppCompatActivity() {

    private lateinit var autenticacion : FirebaseAuth
    private lateinit var interfaz: ActivityInicioSesionBinding
    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        interfaz = ActivityInicioSesionBinding.inflate(layoutInflater)
        setContentView(interfaz.root)

        FirebaseApp.initializeApp(this)
        autenticacion = Firebase.auth

        usuarioViewModel = ViewModelProvider(this)[UsuarioViewModel::class.java]

        interfaz.btnRegistrar.setOnClickListener {

            registrarse()

        }

    } //Fin.

    private fun registrarse(){

        var nombre = interfaz.etNombre.text.toString().trim()
        var correo = interfaz.etCorreo.text.toString().trim()
        var contrasennia = interfaz.etContrasennia.text.toString().trim()

        if(!(nombre.isNullOrEmpty() && correo.isNullOrEmpty() && contrasennia.isNullOrEmpty())){

            autenticacion.createUserWithEmailAndPassword(correo,contrasennia)
                .addOnCompleteListener(this){

                    tarea->

                    if (tarea.isSuccessful){

                        var usuario = autenticacion.currentUser

                        var nuevoUsuario = Usuario(usuario!!.uid,nombre,usuario.email.toString())

                        usuarioViewModel.registrarUsuario(nuevoUsuario)

                        actualizar(usuario)

                    } else{

                        Toast.makeText(this,"Pinche se mama",Toast.LENGTH_SHORT).show()

                    }

                } //Fin del addOnCompleteListener.

        } else{

            Toast.makeText(this,"Llene los campos, puto.",Toast.LENGTH_SHORT).show()

        }

    } //Fin de registrarse.

    private fun iniciarSesion(){



    }

    private fun actualizar(usuario: FirebaseUser?){

        if(usuario!=null){

            var intento = Intent(this,Celulares::class.java)
            startActivity(intento)

        }

    }

    override fun onStart() {

        super.onStart()

        var usuario = autenticacion.currentUser

        actualizar(usuario)

    }

} //Fin.