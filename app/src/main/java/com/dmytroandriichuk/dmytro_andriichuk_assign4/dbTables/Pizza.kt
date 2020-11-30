package com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Pizza (
    @PrimaryKey(autoGenerate = true) val pizzaId: Int,
    val pizzaName: String,
    val price: Double,
    val toppings: String
) {
    override fun toString(): String {
        return "Pizza(pizzaId=$pizzaId, pizzaName='$pizzaName', price=$price, toppings='$toppings')"
    }
}