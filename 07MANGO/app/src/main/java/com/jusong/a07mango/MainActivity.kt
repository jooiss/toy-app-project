package com.jusong.a07mango

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    //data model이 들어갈 list 생성
    private val items = mutableListOf<ContentsModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //data 가져오기
        items.add(
            //url, imageUrl, titleText
            ContentsModel("https://www.mangoplate.com/restaurants/XRoMziImmYCC",
                "https://mp-seoul-image-production-s3.mangoplate.com/2252480_1673490011938600.jpg",
                "뉴욕택시디저트"
            )
        )

        //recyclerView 연결하기
        val recyclerview = findViewById<RecyclerView>(R.id.rv)
        val rvAdapter = RVAdapter(items)
        recyclerview.adapter = rvAdapter

        recyclerview.layoutManager = LinearLayoutManager(this)

        //19.03
    }
}