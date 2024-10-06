package com.example.tugastaskreminder

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tugastaskreminder.databinding.ActivitySecondBinding
import java.util.Calendar
import java.util.Locale

class SecondActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }
    private var date: String? = null
    private var time: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val stringRepeat = resources.getStringArray(R.array.repeat)

        with(binding) {
            val adapter = ArrayAdapter(this@SecondActivity,
                R.layout.spinner_item, stringRepeat)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerRepeat.adapter = adapter

            // milih tanggal
            binding.txtSelectDate.setOnClickListener {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(this@SecondActivity, { _, selectedYear, selectedMonth, selectedDay ->
                    date = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    binding.txtSelectDate.setText(date)
                }, year, month, day)
                datePickerDialog.show()
            }

            val timePicker = binding.timePicker
            timePicker.setIs24HourView(true)

            timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
                time = String.format("%02d:%02d", hourOfDay, minute)
                Toast.makeText(this@SecondActivity, "Time Selected: $time", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun showAlert(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("SimpliRemind")
        builder.setMessage("Do you want to add this as a new task?")
        builder.setPositiveButton("Yes") { _, _ ->
            val title = binding.editTitle.text.toString()
            val intentToThirdActivity = Intent(this, ThirdActivity::class.java).apply {
                putExtra("TITLE", title)
                putExtra("DATE", date)
                putExtra("TIME", time)
                putExtra("REPEAT", binding.spinnerRepeat.selectedItem.toString())
            }
            startActivity(intentToThirdActivity)
        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }

}
