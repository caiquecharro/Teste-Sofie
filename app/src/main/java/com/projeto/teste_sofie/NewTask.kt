package com.projeto.teste_sofie

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.projeto.teste_sofie.objects.SaveData
import kotlinx.android.synthetic.main.activity_new_task.*
import kotlinx.android.synthetic.main.card.*


class NewTask : AppCompatActivity() {

    var savedData = SaveData()

    var savedEmail = ""
    var savedDesc = ""
    var savedTitle = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)

        getSupportActionBar()!!.setHomeAsUpIndicator(R.drawable.ic_delete)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == R.id.btnSave) {

            savedEmail = txtEmail.text.toString()
            savedTitle = txtTitle.text.toString()
            savedDesc = txtDesc.text.toString()


            if (savedTitle != "" && savedEmail != "" && savedDesc != ""){

                save()

            }else{
                this.view.isEnabled = true
                Toast.makeText(this, "Todos os campos são Obrigatórios.", Toast.LENGTH_SHORT).show();
            }

            return true;
        }

        return when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.mymenu, menu)

        return true
    }

    fun save(){

        savedData.title = savedTitle
        savedData.description = savedDesc
        savedData.email = savedEmail

        var data = "{"+
        "\"title\":" + "\"" + savedData.title + "\","+
                "\"email\":" + "\"" + savedData.email + "\","+
                "\"description\":" + "\"" + savedData.description + "\""+
                "}"

        saveRest(this, data)

    }

    fun saveRest(context: Context, savedData: String) {
        var saveData = savedData
        val queue = Volley.newRequestQueue(context)
        val sr = object : StringRequest(
            Request.Method.POST,
            "https://9g1borgfz0.execute-api.sa-east-1.amazonaws.com/beta/task",
            object : Response.Listener<String> {
                override fun onResponse(response: String) {

                    Toast.makeText(this@NewTask, "Tarefa Salva com sucesso.", Toast.LENGTH_SHORT)
                        .show()

                    this@NewTask.finish()
                }
            },
            object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError) {

                    println(error)

                }
            }) {

            override fun getBodyContentType(): String? {
                return "application/json; charset=utf-8"
            }
            override fun getBody(): ByteArray {
                return savedData.toByteArray(charset("utf-8"))
            }

        }
        queue.add(sr)
    }

}