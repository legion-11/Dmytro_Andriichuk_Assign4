package com.dmytroandriichuk.dmytro_andriichuk_assign4

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.OrderAndPizza


class AdapterOrdersInProgress(
    context: Context,
    private var dataSource:  List<OrderAndPizza?> ): BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): OrderAndPizza? {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: inflater.inflate(R.layout.list_item_order, parent, false)
        val pizzaName = view.findViewById(R.id.pizzaName) as TextView
        val toppings = view.findViewById(R.id.toppings) as TextView
        val quantnty = view.findViewById<TextView>(R.id.quantnty)
        val cb = view.findViewById<CheckBox>(R.id.statusOrder)
        Log.i(TAG, "getView: $position")
        val item = getItem(position)
        if (item != null) {
            pizzaName.text = item.pizza.pizzaName
            toppings.text = item.pizza.toppings
            quantnty.text = item.order.quantity.toString()
            cb.isChecked = item.order.status != "in progress"
        }
        return view
    }
}