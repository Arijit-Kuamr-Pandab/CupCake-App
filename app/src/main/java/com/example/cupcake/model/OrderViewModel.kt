package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class OrderViewModel: ViewModel() {
    private val _orderQuantity = MutableLiveData<Int>()
    val orderQuantity: LiveData<Int> = _orderQuantity

    private val _cupCakeFlavor = MutableLiveData<String>()
    val cupCakeFlavor: LiveData<String> = _cupCakeFlavor

    private val _pickUpDate = MutableLiveData<String>()
    val pickUpDate: LiveData<String> = _pickUpDate

    private val _price = MutableLiveData<Double>()
    val price: LiveData<Double> = _price

    val getOrderDate = getPickupOptons()

    init {
        resetOrder()
    }

    fun setQuantity(numberCupcakes: Int){
        _orderQuantity.value = numberCupcakes
    }

    fun setFlavor(desiredFlavor: String){
        _cupCakeFlavor.value = desiredFlavor
    }

    fun setDate(pickupDate: String){
        _pickUpDate.value = pickupDate
    }

    fun hasNoFlavorSet(): Boolean{
        return _cupCakeFlavor.value.isNullOrBlank()
    }

    fun getPickupOptons(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()

        repeat(4){
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }

        return options
    }

    fun resetOrder() {
        _orderQuantity.value = 0
        _pickUpDate.value = getOrderDate[0]
        _cupCakeFlavor.value = ""
        _price.value = 0.0
    }

}