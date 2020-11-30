package com.dmytroandriichuk.dmytro_andriichuk_assign4.daos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.Customer


@Dao
interface CustomerDao {
    @Insert
    fun insertCustomer(person: Customer?)

    @Update
    fun updateCustomer(person: Customer?)

    @Query("SELECT * FROM customer")
    fun getAllCustomers(): LiveData<List<Customer>>

    //get customer by username & password
    @Query("SELECT * FROM customer WHERE userName = :userName AND password = :password")
    fun getCustomer(userName: String, password: String): Customer?

    //get custoomer by id
    @Query("SELECT * FROM customer WHERE customerId = :id")
    fun getCustomer(id: Int): Customer

    //get number of customers with this username
    @Query("SELECT COUNT(*) FROM customer WHERE userName = :userName")
    fun customerExists(userName: String): Int
}