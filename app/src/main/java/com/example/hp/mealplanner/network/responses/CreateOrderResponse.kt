package com.example.hp.mealplanner.network.responses

import com.google.gson.annotations.SerializedName

class CreateOrderResponse {
    
    @SerializedName("code")
    var code : Int = 0

    @SerializedName("message")
    var message : String = ""
}