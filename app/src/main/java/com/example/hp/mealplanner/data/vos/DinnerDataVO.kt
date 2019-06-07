package com.example.hp.mealplanner.data.vos

import com.google.gson.annotations.SerializedName

class DinnerDataVO {

    @SerializedName("id")
    var id : String = ""

    @SerializedName("name")
    var name : String = ""

    @SerializedName("photo")
    var photo : String = ""

    @SerializedName("dinnerGram")
    var dinnerGram : Int = 0

    @SerializedName("calories")
    var calories : Int = 0

//    @SerializedName("ingredients")
//    var ingredients : String = ""

}