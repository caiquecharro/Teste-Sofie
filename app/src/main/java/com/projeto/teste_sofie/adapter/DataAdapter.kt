package com.projeto.teste_sofie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.projeto.teste_sofie.R
import com.projeto.teste_sofie.objects.Data
import com.projeto.teste_sofie.objects.DataList
import kotlinx.android.synthetic.main.card.view.*
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class DataAdapter (private val context: Context, private val list: DataList?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var ph: RecyclerView.ViewHolder? = null


        val v = LayoutInflater.from(context).inflate(R.layout.card, parent, false)
        ph = DataHolder(v)

        return ph

    }

    override fun getItemCount(): Int {

        var size = list!!.DataList.size

        return size

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item = list!!.DataList[position]

        (holder as? DataHolder)?.bindView(item)

    }

    class DataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var lblTitle: TextView
        var lblDesc: TextView

        var data: Data? = null


        init {

            lblTitle= itemView.findViewById(R.id.lblTitle)
            lblDesc = itemView.findViewById(R.id.lblDesc)
        }


        fun bindView(data: Data) {

            this.data = data


            lblTitle.text = data.title
            lblDesc.text = data.description

        }
    }


}