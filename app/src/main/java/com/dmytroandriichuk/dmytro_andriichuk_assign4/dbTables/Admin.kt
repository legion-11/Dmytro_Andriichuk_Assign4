package com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Admin(
    @PrimaryKey(autoGenerate = true) val employeeId: Int,
    val userName: String,
    val password: String,
    val firstname: String,
    val lasttname: String
) {
    override fun toString(): String {
        return "Admin(employeeId=$employeeId, userName=$userName, password=$password, firstname=$firstname, lasttname=$lasttname)"
    }
}