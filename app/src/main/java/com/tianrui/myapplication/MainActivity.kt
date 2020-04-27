@file:Suppress("DEPRECATION")

package com.tianrui.myapplication

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn_up: Button = findViewById(R.id.btn_up)
        //引用按钮
        val btn_down: Button = findViewById(R.id.btn_down)
        //引用按钮
        val btn_reset: Button = findViewById(R.id.btn_reset)
        //引用按钮
        val btn_about: Button = findViewById(R.id.btn_about)
        //引用按钮
        val btn_author: Button = findViewById(R.id.btn_author)
        //引用按钮
        val resultText: TextView = findViewById(R.id.digital)
        //引用文本
        val btn_history: Button = findViewById(R.id.btn_history)
        btn_history.setEnabled(false);//待开发

        btn_about.setOnClickListener { startActivity(Intent(this,AboutActivity::class.java)) }
        btn_author.setOnClickListener { startActivity(Intent(this,AuthorActivity::class.java)) }
        //页面跳转
        btn_up.setOnClickListener { countup() }
        //调用增加
        btn_down.setOnClickListener { countdown() }
        //调用减数
        btn_reset.setOnClickListener {
            getVibrator().vibrate(10)
            if(resultText.text == "0"){
                getVibrator().vibrate(300)
                Toast.makeText(this,getString(R.string.Hasbeenreset),Toast.LENGTH_SHORT).show()
            }else{
                resultText.setText("0")
            }
        }
        //重置计数器为0

        }
    private fun countup(){
        //递增私有函数
        getVibrator().vibrate(10)
        val resultText: TextView = findViewById(R.id.digital)

        //程序启动时初始化文字
        if (resultText.text == "请按下“+”键开始计数") {
            resultText.text = "1"
        } else {
            var resultInt = resultText.text.toString().toInt()

            if (resultInt < 100) {
                resultInt++
                resultText.text = resultInt.toString()
            }else{
                //振动控制
                getVibrator().vibrate(500)
                //当数字大于100时显示Toast
                Toast.makeText(this,getString(R.string.toomuch),Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun countdown(){
        //递减私有函数
        getVibrator().vibrate(10)
        val resultText: TextView = findViewById(R.id.digital)

        if (resultText.text == "请按下“+”键开始计数") {
            resultText.text = "1"
        } else {
            var resultInt = resultText.text.toString().toInt()

            if (resultInt > -100) {
                resultInt--
                resultText.text = resultInt.toString()
            }else{
                getVibrator().vibrate(500)
                //当数字小于100时显示Toast
                Toast.makeText(this,getString(R.string.toomuc),Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun Context.getVibrator() : Vibrator {
        return getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }
}
