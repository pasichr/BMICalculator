package com.example.bmicalculator

import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val weightText=findViewById<EditText>(R.id.etWeight)
        val heightText=findViewById<EditText>(R.id.etHeight)
        val calcButton = findViewById<Button>(R.id.btnCalculate)

        calcButton.setOnClickListener {

            val weight = weightText.text.toString()
            val height = heightText.text.toString()


            if(weight.toFloat()*height.toFloat()>=1){

            }


            if(validationInput(weight,height)) {

                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                // get results to two decimal places
                val bmi2Digits = String.format("%.2f", bmi).toFloat()
                displayResults(bmi2Digits)
            }
        }
    }
    private fun validationInput(weight:String, height:String):Boolean{
        return when {


            weight.isNullOrEmpty() ->{
                Toast.makeText(this,"input your Weight",Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty() ->{
                Toast.makeText(this,"Now input your Height! cutiee😁",Toast.LENGTH_SHORT).show()
                return false
            }
            else -> {
                return true
            }
        }
    }
     private fun displayResults(bmi: Float) {
         val resultIndex =findViewById<TextView>(R.id.tvIndex)
         val resultDescription=findViewById<TextView>(R.id.tvResults)
         val info=findViewById<TextView>(R.id.tvInfo)

         resultIndex.text=bmi.toString()
         info.text="Normal Range is 18.5 -24.9"

         var resultText=""
         var color=0

         when{
             bmi<18.50->{
                 resultText="Under Weight"
                 color=R.color.under_weight
             }
             bmi in 18.50..24.99->{
                 resultText="Healthy"
                 color=R.color.normal
             }
             bmi in 25.00..29.99->{
                 resultText="Over Weight"
                 color=R.color.over_weight
             }
             bmi > 29.99->{
                 resultText="Obese"
                 color=R.color.obose
             }
         }
         resultDescription.setTextColor(ContextCompat.getColor(this,color))
         resultDescription.text=resultText
    }
}


