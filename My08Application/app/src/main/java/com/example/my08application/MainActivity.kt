package com.example.my08application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.View.OnLongClickListener
import com.example.my08application.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // setContentView(R.layout.activity_main)

        binding.helloBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.d("mobileApp", "helloBtn_CLICK")
            }
        })


        binding.helloBtn.setOnLongClickListener(object: View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                Log.d("mobileApp", "helloBtn_Long_CLICK")
                return true
            }


        })

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d("mobileApp", " MotionEvent.ACTION_DOWN")
            }
            MotionEvent.ACTION_UP -> {
                Log.d("mobileApp", " MotionEvent.ACTION_UP")
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_0 -> {
                Log.d("mobileApp", "KeyEvent.KEYCODE_0")
            }
            KeyEvent.KEYCODE_A -> {
                Log.d("mobileApp", "KeyEvent.KEYCODE_A")
            }
            // KeyEvent.KEYCODE_BACK -> {
            // Log.d("mobileApp", "KeyEvent.KEYCODE_BACK")
            //}
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onBackPressed() {
        Log.d("mobileApp", "onBackPressed_KeyEvent.KEYCODE_BACK")
    }

}