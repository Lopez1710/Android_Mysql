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
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class RegistroActivity : AppCompatActivity() {
    private lateinit var etNombre:EditText
    private lateinit var etEmail:EditText
    private lateinit var etTelefono:EditText
    private lateinit var etPass:EditText
    private lateinit var etid:EditText
    private lateinit var prosesoEnCola:RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        etNombre = findViewById(R.id.et_nombre)
        etEmail = findViewById(R.id.et_email)
        etTelefono = findViewById(R.id.et_telefono)
        etPass = findViewById(R.id.et_pass)
        etid = findViewById(R.id.et_id)
        etid.setText(intent.getStringExtra("id").toString())

        val id:String? = intent.getStringExtra("id").toString()

        prosesoEnCola= Volley.newRequestQueue(this)

        val url = "http://192.168.101.14/android_mysql/registro.php?id=$id"
        val consulta = JsonObjectRequest(Request.Method.GET,url,null,
            { Respuesta->
                etNombre.setText(Respuesta.getString("nombre"))
                etEmail.setText(Respuesta.getString("email"))
                etTelefono.setText(Respuesta.getString("telefono"))
                etPass.setText(Respuesta.getString("pass"))
                Toast.makeText(this, "Datos obtenidos", Toast.LENGTH_SHORT).show()
            }, { error->
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                }

        )

        prosesoEnCola.add(consulta)


    }

    fun regresar (vista : View){

        var ventana:Intent = Intent(this,MainActivity::class.java)
        startActivity(ventana)
    }
    fun eliminar(vista: View){
        val url = "http://192.168.101.14/android_mysql/eliminar.php"
        prosesoEnCola = Volley.newRequestQueue(this)
        var respuestad = object :StringRequest(Request.Method.POST,url,
        Response.Listener {respuesra->
            limpiar()
            Toast.makeText(this, "El usuario se a eliminado", Toast.LENGTH_SHORT).show()
        },
        Response.ErrorListener { error ->
            Toast.makeText(this, "error al borrar", Toast.LENGTH_SHORT).show()

        }){
            override fun getParams(): MutableMap<String, String>? {
                val parametros = HashMap<String,String>()
                parametros.put("id",etid.text.toString())
                return parametros
            }
        }
        prosesoEnCola.add(respuestad)
    }
    fun editar(vista: View){
        val url = "http://192.168.101.14/android_mysql/editar.php?"
        prosesoEnCola = Volley.newRequestQueue(this)
        var respuestad = object :StringRequest(Request.Method.POST,url,
            Response.Listener {respuesra->

               Toast.makeText(this, "El usuario se a editado", Toast.LENGTH_SHORT).show()

            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "error al borrar", Toast.LENGTH_SHORT).show()

            }){
            override fun getParams(): MutableMap<String, String>? {
                val parametros = HashMap<String,String>()
                parametros.put("id",etid.text.toString())
                parametros.put("nombre",etNombre.text.toString())
                parametros.put("email",etEmail.text.toString())
                parametros.put("telefono",etTelefono.text.toString())
                parametros.put("pass",etPass.text.toString())
                return parametros
            }
        }
        prosesoEnCola.add(respuestad)
    }

    fun limpiar(){
        etNombre.setText("")
        etEmail.setText("")
        etTelefono.setText("")
        etPass.setText("")
        etid.setText("")
    }
}