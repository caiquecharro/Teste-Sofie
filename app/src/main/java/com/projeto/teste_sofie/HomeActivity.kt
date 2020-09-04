package com.projeto.teste_sofie

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.projeto.teste_sofie.adapter.DataAdapter
import com.projeto.teste_sofie.objects.Data
import com.projeto.teste_sofie.objects.DataList
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    var mLayoutManager: RecyclerView.LayoutManager? = null
    var mAdapter: DataAdapter? = null

    var list : DataList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        getData(this,"task")

    }

    fun getData(
        context: Context,
        service: String
    ) {
        val queue = Volley.newRequestQueue(context)
        val sr = object : StringRequest(
            Request.Method.GET,
            "https://9g1borgfz0.execute-api.sa-east-1.amazonaws.com/beta/" + service,
            object : Response.Listener<String> {
                override fun onResponse(response: String) {

                    println(response)

                    var gson = Gson()

                    var json = response

                    list  = gson.fromJson(json, DataList::class.java)


                    mLayoutManager = LinearLayoutManager(this@HomeActivity)
                    mRecyclerView.layoutManager = mLayoutManager

                    mAdapter = DataAdapter(this@HomeActivity, list)
                    mRecyclerView.adapter = mAdapter

                }
            },
            object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError) {

                    println(error)

                }
            }) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()



                return params
            }

            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params = HashMap<String, String>()
                params["Content-Type"] = "application/x-www-form-urlencoded"
                return params
            }
        }
        queue.add(sr)
    }
}