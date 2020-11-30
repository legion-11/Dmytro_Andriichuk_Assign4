package com.dmytroandriichuk.dmytro_andriichuk_assign4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.activity.viewModels
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.Order
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.Pizza
import com.dmytroandriichuk.dmytro_andriichuk_assign4.models.CustomerModel
import com.dmytroandriichuk.dmytro_andriichuk_assign4.models.CustomerModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

//customer screen to see orders status
class OrderInformation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_information)
        val orderModel: CustomerModel by viewModels { CustomerModelFactory(
            application, intent.getIntExtra("id", -1)
        ) }

        val pizzaName = intent.getStringExtra("typeOfPizza") ?: "error"
        val price = intent.getDoubleExtra("price", 0.0)
        val top = intent.getStringExtra("toppings") ?: "error"
        val customerId = intent.getIntExtra("id", -1)
        CoroutineScope(Dispatchers.IO).launch{
            val pizzaRow = orderModel.insert(Pizza(0, pizzaName, price, top))
            val order = Order(0, customerId, orderModel.getPizzaIdByRow(pizzaRow), null, Date().time.toInt(), 1, "in progress")
            orderModel.insert(order)
        }
        val listView = findViewById<ListView>(R.id.list_view_order)
        orderModel.order.observe(this, {
            listView.adapter = AdapterOrdersInProgress(this, it)
        })
    }
}