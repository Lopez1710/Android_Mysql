package com.example.android_mysql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    private lateinit var etId:EditText
    private lateinit var etNombre:EditText
    private lateinit var etEmail:EditText
    private lateinit var etTelefono:EditText
    private lateinit var etpass:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNombre = findViewById(R.id.et_nombre)
        etEmail = findViewById(R.id.et_email)
        etTelefono = findViewById(R.id.et_telefono)
        etpass = findViewById(R.id.et_pass)
        etId = findViewById(R.id.et_ID)
    }

    fun guardar (vista:View){
        val url = "http://192.168.101.14/android_mysql/insertar.php"
        val procesoEnCola: RequestQueue = Volley.newRequestQueue(this)
        var resultadoPost= object :StringRequest(
            Request.Method.POST,url,
        Response.Listener <String>{ respuesta->
            Toast.makeText(this, "usuario ingresado", Toast.LENGTH_SHORT).show()
            etNombre.setText("")
            etEmail.setText("")
            etTelefono.setText("")
            etpass.setText("")
        },Response.ErrorListener { error->
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()

            }
            ){
            override fun getParams(): MutableMap<String, String>? {
                val parametros = HashMap<String,String>()

                parametros.put("nombre",etNombre.text.toString())
                parametros.put("email",etEmail.text.toString())
                parametros.put("telefono",etTelefono.text.toString())
                parametros.put("pass",etpass.text.toString())

                return parametros
            }
        }

        procesoEnCola.add(resultadoPost)


    }
    fun enviarid (vista:View){

        var ventana:Intent = Intent(this,RegistroActivity::class.java)
        ventana.putExtra("id",etId.text.toString())
        startActivity(ventana)
    }

}