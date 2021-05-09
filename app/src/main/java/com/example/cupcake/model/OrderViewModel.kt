package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel: ViewModel() {
    private val _orderQuantity = MutableLiveData<Int>(0)
    val orderQuantity: LiveData<Int> = _orderQuantity

    private val _cupCakeFlavor = MutableLiveData<String>("")
    val cupCakeFlavor: LiveData<String> = _cupCakeFlavor

    private val _pickUpDate = MutableLiveData<String>("")
    val pickUpDate: LiveData<String> = _pickUpDate

    private val _price = MutableLiveData<Double>(0.0)
    val price: LiveData<Double> = _price


    fun setQuantity(numberCupcakes: Int){
        _orderQuantity.value = numberCupcakes
    }

    fun setFlavor(desiredFlavor: String){
        _cupCakeFlavor.value = desiredFlavor
    }

    fun setDate(pickupDate: String){
        _pickUpDate.value = pickupDate
    }

}