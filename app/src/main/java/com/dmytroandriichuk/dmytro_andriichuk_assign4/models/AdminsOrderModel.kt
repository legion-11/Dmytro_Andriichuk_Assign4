package com.dmytroandriichuk.dmytro_andriichuk_assign4.models

import android.app.Application
import androidx.lifecycle.*
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.Admin
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.CustomerAndOrderAndPizza
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.Order

//view model that provides liveData of Orders in progress
class AdminsOrderModel(application: Application, adminId: Int) : AndroidViewModel(application) {
    private val repository = Repository(application)
    var customersWaiting: LiveData<List<CustomerAndOrderAndPizza?>> = repository.getWaitingOrders().asLiveData()
//    var takenOrders: LiveData<List<CustomerAndOrderAndPizza?>> = repository.getOrdersByAdminId(adminId).asLiveData()

    fun update(order: Order){
        repository.update(order)
    }
}