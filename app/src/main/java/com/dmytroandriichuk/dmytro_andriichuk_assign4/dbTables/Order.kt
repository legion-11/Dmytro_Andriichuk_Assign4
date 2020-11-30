package com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Order (
    @PrimaryKey(autoGenerate = true) val orderId: Int,
    val owner_customerId: Int,
    val order_pizzaId: Int,
    val owner_employeeId: Int?,
    val orderDate: Int,
    val quantity: Int,
    var status: String
)