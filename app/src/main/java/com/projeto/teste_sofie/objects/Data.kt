package com.projeto.teste_sofie.objects

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*


class Data : Serializable {


    @SerializedName("_id") var id : String? = null
    @SerializedName("description") var description : String? = null
    @SerializedName("email") var email : String? = null
    @SerializedName("when") var date : String? = null
    @SerializedName("title") var title : String? = null



}