package com.dmytroandriichuk.dmytro_andriichuk_assign4.models

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dmytroandriichuk.dmytro_andriichuk_assign4.AppDatabase
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.*

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

//link between db and ui
class Repository(context: Context) {
    private val db = AppDatabase.getInstance(context)

    private val adminDao = db.adminDao()
    private val customerDao = db.customerDao()
    private val orderDao = db.orderDao()

    fun insert(admin: Admin){
        CoroutineScope(Dispatchers.IO).launch{ adminDao.insertAdmin(admin) }
    }

    fun checkAdmin(username: String): Deferred<Int> {
        return CoroutineScope((Dispatchers.IO)).async{ adminDao.adminExists(username) }
    }

    suspend fun getAdmin(username: String, password: String): Admin? {
        return CoroutineScope((Dispatchers.IO)).async{ adminDao.getAdmin(username, password) }.await()
    }

    fun insert(customer: Customer){
        CoroutineScope(Dispatchers.IO).launch{ customerDao.insertCustomer(customer) }
    }

    fun checkCustomer(username: String): Deferred<Int> {
        return CoroutineScope((Dispatchers.IO)).async{ customerDao.customerExists(username) }
    }

    suspend fun getCustomer(username: String, password: String): Customer? {
        return CoroutineScope(Dispatchers.IO).async{customerDao.getCustomer(username, password)}.await()
    }
    suspend fun getCustomer(id:Int): Customer {
        return CoroutineScope(Dispatchers.IO).async{customerDao.getCustomer(id)}.await()
    }

    fun getCustomerOrders(id: Int): Flow<List<OrderAndPizza?>> {
        return orderDao.getOrdersByCustomerId(id)
    }

    fun getOrdersByAdminId(id: Int): Flow<List<CustomerAndOrderAndPizza?>> {
        return orderDao.getOrdersByAdminId(id)
    }

    fun getWaitingOrders(): Flow<List<CustomerAndOrderAndPizza?>> {
        return orderDao.getCustomersWhoAreWaiting("in progress")
    }

    fun update(order: Order){
        orderDao.update(order)
    }

    fun insert(order: Order){
        orderDao.insert(order)
    }

    fun insert(order: Pizza): Long{
        return orderDao.insert(order)
    }

    fun get(order: Long): Int{
        return orderDao.getPizza(order)
    }

    fun update(customer: Customer){
        customerDao.updateCustomer(customer)
    }

}