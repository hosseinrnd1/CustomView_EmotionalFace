package com.example.customview_emotionalface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customview_emotionalface.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.happyButton.setOnClickListener {
            binding.EmotionalFace.happinessState=EmotionalFaceView.HAPPY
        }

        binding.sadButton.setOnClickListener {
            binding.EmotionalFace.happinessState=EmotionalFaceView.SAD
        }
    }
}