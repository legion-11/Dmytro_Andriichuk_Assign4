package com.dmytroandriichuk.dmytro_andriichuk_assign4.daos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.Admin

@Dao
interface AdminDao {
    @Insert
    fun insertAdmin(person: Admin)

    @Update
    fun updateAdmin(person: Admin)

    //get Admin by password and username
    @Query("SELECT * FROM admin WHERE userName = :userName AND password = :password")
    fun getAdmin(userName: String, password: String): Admin?

    //get number of users with this number of username so we can check uniqueness
    @Query("SELECT COUNT(*) FROM admin WHERE userName = :userName")
    fun adminExists(userName: String): Int
}