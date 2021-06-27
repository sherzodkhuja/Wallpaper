package com.example.retrofit.models

import java.io.Serializable

data class Category(
    var name: String,
    val imgList: List<String>
) : Serializable