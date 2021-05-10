package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
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
    val price: LiveData<String> = Transformations.map(_price){
        NumberFormat.getCurrencyInstance().format(it)
    }

    val getOrderDate = getPickupOptons()

    private val PRICE_PER_CUPCAKE = 2.00
    private val PRICE_FOR_SAME_DAY_PICKUP = 3.00

    init {
        resetOrder()
    }

    /**
     * for setting the Quantity of the cupcake
     */
    fun setQuantity(numberCupcakes: Int){
        _orderQuantity.value = numberCupcakes
        updatePrice()
    }

    /**
     * for setting Flavor of cupcake
     */
    fun setFlavor(desiredFlavor: String){
        _cupCakeFlavor.value = desiredFlavor
    }

    /**
     * for setting PickUp date of cupcake
     */
    fun setDate(pickupDate: String){
        _pickUpDate.value = pickupDate
        updatePrice()
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
        _pickUpDate.value = ""
        _cupCakeFlavor.value = ""
        _price.value = 0.0
    }

    private fun updatePrice(){
        _price.value = ((orderQuantity.value ?: 0) * PRICE_PER_CUPCAKE)

        // If the user selected the first option (today) for pickup, add the surcharge
        if (getOrderDate[0] == _pickUpDate.value) {
            _price.value = _price.value!! + PRICE_FOR_SAME_DAY_PICKUP
        }
    }

}