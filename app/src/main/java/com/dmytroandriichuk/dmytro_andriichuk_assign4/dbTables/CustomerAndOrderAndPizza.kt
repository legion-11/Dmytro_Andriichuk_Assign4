package com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables

import androidx.room.Embedded
import androidx.room.Relation

data class CustomerAndOrderAndPizza (
        @Embedded val order: OrderAndPizza,
        @Relation(
                parentColumn = "owner_customerId",
                entityColumn = "customerId",
                entity = Customer::class
        )
        val customer: Customer
) {
    override fun toString(): String {
        return customer.toString() + "\n" + order.toString()
    }
}