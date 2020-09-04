package com.projeto.teste_sofie.objects

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DataList : Serializable {


    @SerializedName("data") var DataList = ArrayList<Data>()


}