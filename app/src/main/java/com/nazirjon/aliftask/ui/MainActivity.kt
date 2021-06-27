package com.nazirjon.aliftask.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nazirjon.aliftask.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}