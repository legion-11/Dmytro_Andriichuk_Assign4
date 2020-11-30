package com.dmytroandriichuk.dmytro_andriichuk_assign4

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dmytroandriichuk.dmytro_andriichuk_assign4.daos.AdminDao
import com.dmytroandriichuk.dmytro_andriichuk_assign4.daos.CustomerDao
import com.dmytroandriichuk.dmytro_andriichuk_assign4.daos.OrderDao
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.Admin
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.Customer
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.Order
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.Pizza


@Database(
    entities = arrayOf(Customer::class, Admin::class, Order::class, Pizza::class),
    version = 2
)
//create singleton of db
abstract class AppDatabase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao
    abstract fun adminDao(): AdminDao
    abstract fun orderDao(): OrderDao

    companion object {

        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java, "database"
            )
//                .fallbackToDestructiveMigration()
//                .addMigrations(MIGRATION_1_2)
                .build()
                .also{Log.i("AppDatabase", "database created")}

    }
}