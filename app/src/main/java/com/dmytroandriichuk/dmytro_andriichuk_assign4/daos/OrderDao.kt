package com.dmytroandriichuk.dmytro_andriichuk_assign4.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.CustomerAndOrderAndPizza
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.Order
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.OrderAndPizza
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.Pizza
import kotlinx.coroutines.flow.Flow
import java.sql.RowId

@Dao
interface OrderDao {

    @Transaction
    @Query("SELECT * FROM `Order` WHERE owner_customerId=:id")
    fun getOrdersByCustomerId(id: Int): Flow<List<OrderAndPizza?>>

    @Transaction
    @Query("SELECT * FROM `Order` WHERE status=:s")
    fun getCustomersWhoAreWaiting(s: String): Flow<List<CustomerAndOrderAndPizza?>>

    @Transaction
    @Query("SELECT * FROM `Order` WHERE owner_employeeId=:id")
    fun getOrdersByAdminId(id: Int): Flow<List<CustomerAndOrderAndPizza?>>

    //returning the rowId to get Pizza when it was created
    @Insert
    fun insert(pizza: Pizza): Long

    @Insert
    fun insert(order: Order)

    @Update
    fun update(order: Order)

    //get pizza by row
    @Query("SELECT pizzaId FROM pizza WHERE rowId=:rowId")
    fun getPizza(rowId: Long): Int
}