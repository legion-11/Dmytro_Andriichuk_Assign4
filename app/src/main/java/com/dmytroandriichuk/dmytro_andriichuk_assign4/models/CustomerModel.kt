package com.dmytroandriichuk.dmytro_andriichuk_assign4.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.Order
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.OrderAndPizza
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.Pizza

// provide info about order state
class CustomerModel(application: Application, id: Int) : AndroidViewModel(application) {
    private val repository = Repository(application)
    var order: LiveData<List<OrderAndPizza?>> = repository.getCustomerOrders(id).asLiveData()

    fun insert(order: Order){
        repository.insert(order)
    }

    fun insert(order: Pizza): Long{
        return repository.insert(order)
    }
    fun getPizzaIdByRow(order: Long): Int{
        return repository.get(order)
    }
}