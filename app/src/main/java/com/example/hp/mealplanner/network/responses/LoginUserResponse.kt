package com.example.hp.mealplanner.network.responses

import com.google.gson.annotations.SerializedName

class LoginUserResponse {

    @SerializedName("token")
    var token : String = ""
}