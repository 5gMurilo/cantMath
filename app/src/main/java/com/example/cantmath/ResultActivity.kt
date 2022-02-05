package com.example.cantmath

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    private var accountResult: Float? = null
    private var numberOfPerson: Int? = null
    private var tip : Float? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        getFromIntent()
        initData()
        finishActivity()
    }

    private fun initData() {
        val stringExplain =  "Split between $numberOfPerson people with $tip% tip"
        val stringMoney = "R$ ${accountResult!!.format(2)}"
        explain.text = stringExplain
        money.text = stringMoney
    }

    fun Float.format(digits:Int) = "%.${digits}f".format(this)

    private fun getFromIntent() {
        accountResult = intent?.extras?.getFloat("accountResult")
        numberOfPerson = intent?.extras?.getInt("numberOfPerson")
        tip = intent?.extras?.getFloat("tip")
    }

    private fun finishActivity() {
        recalculateButton.setOnClickListener {
            finish()
        }
    }
}