package com.evi.selectdaterange

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.evi.selectdaterange.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import kt.text.SimpleDateFormat
import kt.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            showDataRangePicker()
        }
    }


    private fun showDataRangePicker() {

        val dateRangePicker =
                MaterialDatePicker
                        .Builder.dateRangePicker()
                        .setTitleText("Select Date")
                        .build()

        dateRangePicker.show(
                supportFragmentManager,
                "select_date_range"
        )

        dateRangePicker.addOnPositiveButtonClickListener { dateSelected ->

            val startDate = dateSelected.first
            val endDate = dateSelected.second

            if (startDate != null && endDate != null) {
                binding.tvRangeDate.text =
                        "Reserved\nStartDate: ${convertLongToTime(startDate)}\n" +
                        "EndDate: ${convertLongToTime(endDate)}"
            }
        }

    }


    private fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat(
                "dd.MM.yyyy",
                Locale.getDefault())
        return format.format(date)
    }
}
