package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var lastNumeric = false
    private var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun onDigit(view: View){
        when (view) {
            btnClear -> {
                tvDisplayInput.text = ""
                lastNumeric = false
                lastDot = false
            }
            btnDecimal -> {
                if (lastNumeric && !lastDot){
                    tvDisplayInput.append(".")
                    lastNumeric = false
                    lastDot = true
                }
            }

            btnAdd -> appendOperator(view)
            btnDivide -> appendOperator(view)
            btnAstric -> appendOperator(view)
            btnSub -> appendOperator(view)

            btnEqual -> onEqual()
            else -> {
                tvDisplayInput.append((view as Button).text)
                lastNumeric = true
            }
        }
    }

    private fun appendOperator(view: View){
        if (lastNumeric && !isOperatorAdded(tvDisplayInput.text.toString())){
            tvDisplayInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    private fun isOperatorAdded(value: String) : Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("+") ||value.contains("-")||value.contains("*")||value.contains("/")
        }
    }

    private fun onEqual(){
        var tvValue = tvDisplayInput.text.toString()
        var prefix = ""

        if (lastNumeric){

            if (tvValue.startsWith("-")){
                prefix = "-"
                tvValue = tvValue.substring(1)
            }

            try {
                when {
                    tvValue.contains("-") -> {
                        val splitValue = tvValue.split("-")
                        var one = splitValue[0]
                        val two = splitValue[1]

                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }
                        tvDisplayInput.text = (one.toDouble()-two.toDouble()).toString()

                    }
                    tvValue.contains("+") -> {
                        val splitValue = tvValue.split("+")
                        var one = splitValue[0]
                        val two = splitValue[1]

                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }
                        tvDisplayInput.text = (one.toDouble()+two.toDouble()).toString()
                    }
                    tvValue.contains("*") -> {
                        val splitValue = tvValue.split("*")
                        var one = splitValue[0]
                        val two = splitValue[1]

                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }
                        tvDisplayInput.text = (one.toDouble()*two.toDouble()).toString()
                    }
                    tvValue.contains("/") -> {
                        val splitValue = tvValue.split("/")
                        var one = splitValue[0]
                        val two = splitValue[1]

                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }
                        tvDisplayInput.text = (one.toDouble()/two.toDouble()).toString()
                    }
                }

            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

}