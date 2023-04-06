package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsersActivity : AppCompatActivity() {
    private val BASE_URL = "https://jsonplaceholder.typicode.com"
    var listUsers = arrayListOf<UserModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(API::class.java).getUsers().enqueue(object : Callback<List<UserModel>> {
            override fun onResponse(
                call: Call<List<UserModel>>,
                response: Response<List<UserModel>>
        ) {
                if (response.isSuccessful) {
                    for (i in response.body()!!) {
                        val a = UserModel(i.id, i.name, i.username, i.email)
                        listUsers.add(a)
                }
                    Toast.makeText(this@UsersActivity, listUsers.toString(), Toast.LENGTH_SHORT)
                        .show()
            }
        }

            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                Toast.makeText(this@UsersActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}
