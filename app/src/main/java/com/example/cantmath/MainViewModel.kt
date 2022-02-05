package com.example.cantmath

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var mPeople = MutableLiveData<String>().apply { value = numberOfPeople.toString() }
    private var numberOfPeople: Int = 0

    private fun setNumberOfPeople() {
        mPeople.value = numberOfPeople.toString()
    }

    private fun increaseValidation() {
        numberOfPeople ++
        setNumberOfPeople()
    }

    private fun decreaseValidation() {
        if(numberOfPeople > 0 ){
            numberOfPeople --
        }
        setNumberOfPeople()
    }

    fun decrease() {
        decreaseValidation()
    }

    fun increase() {
        increaseValidation()
    }


}