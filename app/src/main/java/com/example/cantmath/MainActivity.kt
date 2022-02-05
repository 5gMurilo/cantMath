package com.example.cantmath

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    private var valuePerPerson:Float = 0f
    private var tip : Float? = 0f
    private var account : Float? = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
        listeners()
    }

    private fun initData() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.mPeople.observe(this) {valor -> number_of_person.text = valor }
    }

    private fun listeners() {
        minusButton.setOnClickListener {
            if(mainViewModel.mPeople.value.toString().toInt() > 0){
                mainViewModel.decrease()
            }else{
                Toast.makeText(this, "Isn't possible decrease 0 in this situation", Toast.LENGTH_SHORT).show()
            }
        }

        plusButton.setOnClickListener {
            mainViewModel.increase()
        }

        btnCalc.setOnClickListener {
            val intent =  Intent(this, ResultActivity::class.java)
            var calc = calculate()
            intent.putExtra("accountResult",calc)
            intent.putExtra("numberOfPerson", mainViewModel.mPeople.value.toString().toInt())
            intent.putExtra("tip", tip)
            startActivity(intent)
        }

    }

    private fun calculate():Float {
        if(account == null && tip == null){
            Toast.makeText(this, "Enter valid values in the fields", Toast.LENGTH_SHORT).show()
        }else if(mainViewModel.mPeople.value.toString().toInt() == 0){
            Toast.makeText(this, "Enter the number of people who will split the bill", Toast.LENGTH_SHORT).show()
        }

        account = edtAccount.text.toString().toFloat()
        tip = edtTip.text.toString().toFloat()

        valuePerPerson = (account!! + account!!.times(tip!!.div(100))) / mainViewModel.mPeople.value.toString().toInt()
        return valuePerPerson
    }
}