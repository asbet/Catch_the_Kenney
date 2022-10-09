package com.betulas.catchthekennykotlinversion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.betulas.catchthekennykotlinversion.databinding.ActivityPlayGameBinding
import java.util.ArrayList
import kotlin.random.Random

class playGame : AppCompatActivity() {
    private lateinit var binding: ActivityPlayGameBinding
    var number=0
    var score=0
    var imageArrayList=ArrayList<ImageView>()
    var handler=Handler(Looper.getMainLooper())
    var runnable=Runnable{ }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        imageArrayList.add(binding.image1)
        imageArrayList.add(binding.image2)
        imageArrayList.add(binding.image3)
        imageArrayList.add(binding.image4)
        imageArrayList.add(binding.image5)
        imageArrayList.add(binding.image6)
        imageArrayList.add(binding.image7)
        imageArrayList.add(binding.image8)
        imageArrayList.add(binding.image9)

        hideImages()

        object:CountDownTimer(20000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.timerTextView.text="Left: ${millisUntilFinished/1000}"
                number=0
            }

            override fun onFinish() {
                binding.timerTextView.text="Time: 0"
                handler.removeCallbacks(runnable)
                for (image in imageArrayList){
                    image.visibility=View.INVISIBLE

                }
                val alert=AlertDialog.Builder(this@playGame)
                alert.setTitle("Score: $score")
                alert.setMessage("Do you want to play again?")
                alert.setPositiveButton("Yes") {dialog, which ->
                    val intent=intent
                    finish()
                    startActivity(intent)
                }

                alert.setNegativeButton("No"){dialog, which ->
                    Toast.makeText(applicationContext,"Game Over",Toast.LENGTH_LONG).show()
                    val intent= Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                alert.show()
            }

        }.start()
    }
    fun hideImages(){
        runnable=object : Runnable {
            override fun run() {
                for (image in imageArrayList){
                    image.visibility=View.INVISIBLE
                }
                val random= Random
                val randomIndex=random.nextInt(9)
                imageArrayList[randomIndex].visibility=View.VISIBLE
                handler.postDelayed(runnable,500)
            }
        }
        handler.post(runnable)
    }
    fun kenneyClicked(view: View){
        score =score+1
        binding.scoreTextView.text ="Score: $score"
    }
}

