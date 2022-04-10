package com.celulares.data

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.celulares.model.Usuario
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UsuarioDAO {

    private var coleccionCelulares = "celularesAPP"
    private var fireStoreDataBase: FirebaseFirestore

    init {

        fireStoreDataBase = FirebaseFirestore.getInstance()
        fireStoreDataBase.firestoreSettings = FirebaseFirestoreSettings.Builder().build()

    }



    fun getUsuario3(idUsuario: String): Usuario?{

        var usuario : Usuario? = null

        fireStoreDataBase.collection(coleccionCelulares)
            .document(idUsuario).addSnapshotListener{

                snapshot , error ->

                if(error!=null){

                    return@addSnapshotListener

                }

                if(snapshot!=null){

                    usuario = snapshot.toObject<Usuario>()

                } //Fin.

            } //Fin.

        return usuario

    } //Fin



    /*
    fun getUsuario(idUsuario: String): Usuario?{

        var documentoUsuario = fireStoreDataBase.collection(coleccionCelulares).document(idUsuario)
        var usuario : Usuario? = null

        documentoUsuario.get().addOnSuccessListener{

            snapshot ->

            if(snapshot!=null){

                usuario = snapshot.toObject<Usuario>()

            } else{

                Log.d("demeUsuario","FFFFF")

            }

        }.addOnFailureListener {

                tarea ->

            Log.d("demeUsuario",tarea.message.toString())

        } //Fin.

        return usuario

    } //Fin

    fun registrarUsuario(usuario: Usuario){

        fireStoreDataBase.collection(coleccionCelulares)
            .document(usuario.idUsuario).set(usuario)

    } //Fin.

     */

     fun getUsuario(idUsuario: String, resultListener: ResultListener): Usuario?{


        var usuario : Usuario? = null



         return usuario

    } //Fin

    /*
    fun getUsuario(idUsuario: String): Usuario? {

        var usuario : Usuario? = null

        //Llamar para obtener los documentos.

        fireStoreDataBase.collection(coleccionCelulares) //LugarApp
            .document(idUsuario) //el usuario (correo o ui)
            .addSnapshotListener{snapshot, excepcion -> //Si se da una excepcion al tomar una instantanea, entre a
                //este bloque de codigo.

                if (excepcion!=null){

                    return@addSnapshotListener

                } //Fin del if.

                if (snapshot!=null){


                    usuario = snapshot.toObject<Usuario>()

                } //Fin.

            }  //Fin del addSnapshotListener.

        return usuario

    } //Fin de getLugares.
*/
    suspend fun registrarUsuario(usuario: Usuario){

        fireStoreDataBase.collection(coleccionCelulares)
            .document(usuario.idUsuario).set(usuario)

    } //Fin.

} //Fin de la clase.