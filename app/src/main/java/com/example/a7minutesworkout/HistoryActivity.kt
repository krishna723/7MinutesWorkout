package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minutesworkout.databinding.ActivityHistoryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    private var binding: ActivityHistoryBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarHistory)

        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title="CALCULATE BMI"
        }


        binding?.toolbarHistory?.setNavigationOnClickListener{
            onBackPressed()
        }

        val Dao= (application as WorkOutApp).db!!.historyDao()
        getAllCompletedData(Dao)
    }

    private fun getAllCompletedData(historyDao: HistoryDao){
        lifecycleScope.launch{
            historyDao.fetchAllDates().collect{allCompletedDateList ->
                if(allCompletedDateList.isNotEmpty()){
                    binding?.tvheading?.visibility=View.VISIBLE
                    binding?.rvHistory?.visibility=View.VISIBLE
                    binding?.tvDataNotFound?.visibility=View.INVISIBLE

                    binding?.rvHistory?.layoutManager=LinearLayoutManager(this@HistoryActivity)
                    val dates=ArrayList<String>()
                    for (date in allCompletedDateList){
                        dates.add(date.date)
                    }
                    val historyAdapter=HistoryAdapter(dates)
                    binding?.rvHistory?.adapter=historyAdapter
                }else{
                    binding?.tvheading?.visibility=View.INVISIBLE
                    binding?.rvHistory?.visibility=View.INVISIBLE
                    binding?.tvDataNotFound?.visibility=View.VISIBLE
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}