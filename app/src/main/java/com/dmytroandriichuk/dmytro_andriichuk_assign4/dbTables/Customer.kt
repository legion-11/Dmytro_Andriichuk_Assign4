package com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Customer(
    @PrimaryKey(autoGenerate = true) val customerId: Int,
    var userName: String,
    var password: String,
    var firstname: String,
    var lastname: String,
    var address: String?,
    var city: String?,
    var postalCode: String?
){
    override fun toString(): String {
        return "Customer(customerId=$customerId, userName=$userName, password=$password, firstname=$firstname, lastname=$lastname, address=$address, city=$city, postalCode=$postalCode)"
    }
}


