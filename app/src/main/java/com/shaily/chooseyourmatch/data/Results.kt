package com.shaily.chooseyourmatch.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//@Entity(
//    tableName = "matchResults"
//)
data class Results (

//    @PrimaryKey(autoGenerate = true)
//    var userId: Int? = 0,
    @SerializedName("id") val id : Id,
    @SerializedName("gender") val gender : String,
    @SerializedName("name") val name : Name,
    @SerializedName("location") val location : Location,

    @SerializedName("dob") val dob : Dob,

    @SerializedName("cell") val cell : String,
    @SerializedName("picture") val picture : Picture,
)