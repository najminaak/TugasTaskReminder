package com.example.tugastaskreminder

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tugastaskreminder.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("TITLE")
        val date = intent.getStringExtra("DATE")
        val time = intent.getStringExtra("TIME")
        val repeat = intent.getStringExtra("REPEAT")

        binding.txtTaskTitle.text = title
        binding.txtDate.text = date
        binding.txtTime.text = time
        binding.txtRepeat.text = repeat

        binding.btnAddTaskThird.setOnClickListener {
            val intentToSecondActivity = Intent(this, SecondActivity::class.java)
            startActivity(intentToSecondActivity)
        }
    }
}
