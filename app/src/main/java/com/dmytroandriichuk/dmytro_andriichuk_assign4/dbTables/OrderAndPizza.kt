package com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables

import androidx.room.Embedded
import androidx.room.Relation

//data class for cross referencing
data class OrderAndPizza (

        @Embedded val order: Order,
        @Relation(
                parentColumn = "order_pizzaId",
                entityColumn = "pizzaId"
        )
        val pizza: Pizza
) {
        override fun toString(): String {
                return order.toString() +"\n"+ pizza.toString()+"\n"
        }
}