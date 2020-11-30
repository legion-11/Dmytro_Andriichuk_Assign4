package com.dmytroandriichuk.dmytro_andriichuk_assign4

import android.os.Bundle
import android.widget.CheckBox
import android.widget.ListView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dmytroandriichuk.dmytro_andriichuk_assign4.models.AdminModelFactory
import com.dmytroandriichuk.dmytro_andriichuk_assign4.models.AdminsOrderModel

//admins screen to see orders
class SeeOrdersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_order)

        val orderModel: AdminsOrderModel by viewModels { AdminModelFactory(
            application, intent.getIntExtra(
                "id",
                -1
            )
        ) }

        val listView = findViewById<ListView>(R.id.list_view)
        orderModel.customersWaiting.observe(this, {
            listView.adapter = AdapterCustomersWaiting(this, it, orderModel)
        })

        listView.setOnItemClickListener { parent, view, position, id ->
            view.findViewById<CheckBox>(R.id.status).isChecked = true
        }


//        orderModel.takenOrders.observe(this, {
//            findViewById<ListView>(R.id.list_view).adapter = AdapterCustomersWaiting(this, it)
//        })
    }
}