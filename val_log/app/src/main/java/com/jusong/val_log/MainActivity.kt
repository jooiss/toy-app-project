package com.jusong.val_log
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var value = "여기는 value입니다!"
        val value2 = "여기는 value2입니다!"

        value = "여기는 value가 아닙니다!"
        // value2 = "여기는 value2가 아닙니다!"


        // Log.d("MainActivity", "testLog" )
        val test = "하하안녕"
        Log.e("MainActivity", test) // 오류
        Log.w("MainActivity", test) // 경고
        Log.i("MainActivity", test) // 정보
        Log.d("MainActivity", test) // 디버그
        Log.v("MainActivity", test) // 상세


    }
}