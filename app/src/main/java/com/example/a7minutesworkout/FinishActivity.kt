package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope

import com.example.a7minutesworkout.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {
    private var binding : ActivityFinishBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarFinish)

        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarFinish?.setNavigationOnClickListener{
            onBackPressed()
        }

        binding?.btnFinish?.setOnClickListener{
            finish()
        }

        val Dao= (application as WorkOutApp).db!!.historyDao()

            addDateToDatabase(Dao)

    }
    private fun addDateToDatabase(historyDao: HistoryDao){
        val c= Calendar.getInstance()
        val dateTime=c.time

        val sdf=SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date=sdf.format(dateTime)


        lifecycleScope.launch{
            historyDao.insert(HistoryEntity(date))
        }
    }
}