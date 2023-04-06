package com.example.project

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface API {
    @GET("/users")
    fun getUsers(): Call<List<UserModel>>

    @POST("/posts")
    fun getPosts() : Call<List<UserModel>>
}