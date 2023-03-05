package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityBmiactivityBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    companion object{
        private const val METRIC_UNITS_VIEW="METRIC_UNITS_VIEW"
        private const val US_UNITS_VIEW="US_UNITS_VIEW"
    }
    private var currentVisibleView: String=METRIC_UNITS_VIEW
    private var binding: ActivityBmiactivityBinding?=null
    private var units: Array<out String>? =null
    private var arrayAdapter: ArrayAdapter<String>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBMI)

        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title="CALCULATE BMI"
        }


        binding?.toolbarBMI?.setNavigationOnClickListener{
            onBackPressed()
        }
//        units=resources.getStringArray(R.array.unit)
//        arrayAdapter=ArrayAdapter(this,R.layout.dropdown_item, units as Array<out String>)
//        binding?.atMetricUnit?.setAdapter(arrayAdapter)
        makeVisibleMetricUnitsView()

        binding?.rgUnit?.setOnCheckedChangeListener{_,checkedID: Int->
            if (checkedID==R.id.rbMetricUnits){
                makeVisibleMetricUnitsView()
                binding?.llBMIContainer?.visibility=View.INVISIBLE
            }else{
                makeVisibleUSUnitsView()
                binding?.llBMIContainer?.visibility=View.INVISIBLE
            }

        }

        binding?.btnBMICalculate?.setOnClickListener{
            calculateUnit()

        }
    }

    private fun makeVisibleMetricUnitsView(){
        currentVisibleView=METRIC_UNITS_VIEW
        units=resources.getStringArray(R.array.unit)
        arrayAdapter=ArrayAdapter(this,R.layout.dropdown_item, units as Array<out String>)
        binding?.atMetricUnit?.setAdapter(arrayAdapter)
        binding?.etMetricUnitWeight?.hint="Weight (in kg)"
        clear()

    }
    private fun makeVisibleUSUnitsView(){
        currentVisibleView=US_UNITS_VIEW
        units=resources.getStringArray(R.array.usUnit)
        arrayAdapter=ArrayAdapter(this,R.layout.dropdown_item, units as Array<out String>)
        binding?.atMetricUnit?.setAdapter(arrayAdapter)
        binding?.etMetricUnitWeight?.hint="Weight (in pounds)"
        clear()
    }

    private fun clear(){
        binding?.etMetricUnitWeight?.text!!.clear()
        binding?.etMetricUnitHeight?.text!!.clear()
        binding?.atMetricUnit?.text!!.clear()

    }

    private fun calculateUnit(){
        if(currentVisibleView==METRIC_UNITS_VIEW){
            if(validateMetricUnits()){
                var heightValue: Float?= null
                val weightValue :Float=binding?.etMetricUnitWeight?.text.toString().toFloat()

                if (binding?.atMetricUnit?.text.toString().equals("cm")){
                    heightValue=binding?.etMetricUnitHeight?.text.toString().toFloat()/100

                }else if(binding?.atMetricUnit?.text.toString().equals("feet")){
                    heightValue=binding?.etMetricUnitHeight?.text.toString().toFloat() * 0.3048f

                }

                val bmi=weightValue/(heightValue!!*heightValue!!)
                displayBMIResult(bmi)

            }else{
                Toast.makeText(this@BMIActivity,"Please enter valid value",Toast.LENGTH_LONG).show()

            }
        }else{
            if(validateMetricUnits()){
                var heightValue: Float?= null
                val weightValue :Float=binding?.etMetricUnitWeight?.text.toString().toFloat()

                if (binding?.atMetricUnit?.text.toString().equals("feet")){
                    heightValue=binding?.etMetricUnitHeight?.text.toString().toFloat()*12

                }else if(binding?.atMetricUnit?.text.toString().equals("inch")){
                    heightValue=binding?.etMetricUnitHeight?.text.toString().toFloat()

                }

                val bmi=703*(weightValue/(heightValue!!*heightValue!!))
                displayBMIResult(bmi)

            }else{
                Toast.makeText(this@BMIActivity,"Please enter valid value",Toast.LENGTH_LONG).show()

            }
        }
    }

    private fun displayBMIResult(bmi:Float){
        val bmiLable : String
        val bmiDesc: String

        if(bmi.compareTo(15f)<=0){
            bmiLable="Very severely underweight"
            bmiDesc="Oops! You really need to take better care of yourself! Eat more!"
        }else if(bmi.compareTo(15f) >0 && bmi.compareTo(16f)<=0){
            bmiLable="severely underweight"
            bmiDesc="Oops! You really need to take better care of yourself! Eat more!"
        }else if(bmi.compareTo(16f) >0 && bmi.compareTo(18.5f)<=0){
            bmiLable=" Underweight"
            bmiDesc="Oops! You really need to take better care of yourself! Eat more!"
        }else if(bmi.compareTo(18.5f) >0 && bmi.compareTo(25f)<=0){
            bmiLable="Normal"
            bmiDesc="Congratulations! You are in a good shape!"
        }else if(bmi.compareTo(25f) >0 && bmi.compareTo(30f)<=0){
            bmiLable=" Overweight"
            bmiDesc="Oops! You really need to take better care of yourself! Workout maybe!"
        }else if(bmi.compareTo(30f) >0 && bmi.compareTo(35f)<=0){
            bmiLable="Obese Class | (Moderately obese)"
            bmiDesc="Oops! You really need to take better care of yourself! Workout maybe!"
        }else if(bmi.compareTo(35f) >0 && bmi.compareTo(40f)<=0){
            bmiLable="Obese Class || (Severely obese)"
            bmiDesc="Oops! You really need to take better care of yourself! Workout maybe!"
        }else{
            bmiLable="Obese Class || (Very Severely obese)"
            bmiDesc="OMG! You are in very dangerous condition! ct now!"
        }

        val bmiValue=BigDecimal(bmi.toDouble()).setScale(2,RoundingMode.HALF_EVEN).toString()
        binding?.llBMIContainer?.visibility=View.VISIBLE
        binding?.tvBMIValue?.text=bmiValue
        binding?.tvBMIType?.text=bmiLable.toString()
        binding?.tvBMIDescription?.text=bmiDesc.toString()

    }

    private fun validateMetricUnits(): Boolean{
        var isValid= true
        if (binding?.etMetricUnitWeight?.text.toString().isEmpty()){
            isValid=false
        }else if(binding?.etMetricUnitHeight?.text.toString().isEmpty()){
            isValid=false
        }else if(binding?.atMetricUnit?.text.toString().isEmpty()){
            isValid=false
        }
        return isValid
    }
}