package com.dmytroandriichuk.dmytro_andriichuk_assign4.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.Admin
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.Customer

class UsersModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository(application)

    suspend fun getAdmin(username: String, password: String): Admin? {
        return repository.getAdmin(username, password)
    }

    suspend fun getCustomer(username: String, password: String): Customer? {
        return repository.getCustomer(username, password)
    }

    suspend fun getCustomer(id:Int): Customer {
        return repository.getCustomer(id)
    }

    suspend fun checkUsername(username: String): Int {
        val customerCheck = repository.checkAdmin(username)
        val adminCheck = repository.checkCustomer(username)
        return customerCheck.await() + adminCheck.await()
    }

    fun insert(person: Customer) {
        repository.insert(person)
    }

    fun insert(person: Admin) {
        repository.insert(person)
    }

    fun update(person: Customer){
        repository.update(person)
    }
}