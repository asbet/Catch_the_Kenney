package com.betulas.catchthekennykotlinversion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.betulas.catchthekennykotlinversion.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
    fun startButtonForPlayGame(view : View){
        val intent= Intent(applicationContext,playGame::class.java)
        startActivity(intent)
    }
}


