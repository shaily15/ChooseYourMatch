package com.shaily.chooseyourmatch.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "matchResults"
)
data class Results (

    @PrimaryKey(autoGenerate = true)
    var userId: Int? = null,
    @SerializedName("id") val id : Id,
    @SerializedName("gender") val gender : String,
    @SerializedName("name") val name : Name,
    @SerializedName("location") val location : Location,
    @SerializedName("email") val email : String,
    @SerializedName("dob") val dob : Dob,
    @SerializedName("phone") val phone : String,
    @SerializedName("cell") val cell : String,
    @SerializedName("picture") val picture : Picture,
    @SerializedName("nat") val nat : String
)