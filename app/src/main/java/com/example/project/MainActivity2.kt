package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val a = TASK1("СОБРАТЬ СОВЕЩЯНИЕ","Собрать совещание в здании администрации для решения зимних вопросов","11.12.2022")
        val b = TASK1("СОБРАТЬ СОВЕЩАНИЕ","Собрать совещание в здании администрации для решения зимних вопросов","11.12.2022")
        val list = arrayListOf<TASK1>(a,b)
        rec.adapter = My_Adapter(list)
        rec.layoutManager = LinearLayoutManager(this)
    }
}