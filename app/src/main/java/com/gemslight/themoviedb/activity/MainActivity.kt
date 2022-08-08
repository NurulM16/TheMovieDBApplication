package com.gemslight.themoviedb.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gemslight.themoviedb.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
    }
}