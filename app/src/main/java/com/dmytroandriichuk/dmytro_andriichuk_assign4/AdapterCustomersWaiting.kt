package com.dmytroandriichuk.dmytro_andriichuk_assign4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.CustomerAndOrderAndPizza
import com.dmytroandriichuk.dmytro_andriichuk_assign4.models.AdminsOrderModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//adapter for list for admin screen to see livedata of orders
class AdapterCustomersWaiting(private val context: Context,
                              private var dataSource:  List<CustomerAndOrderAndPizza?>, model: AdminsOrderModel): BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private val model = model
    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): CustomerAndOrderAndPizza? {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //built view of list item
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: inflater.inflate(R.layout.list_item, parent, false)
        val customerName = view.findViewById(R.id.customerName) as TextView
        val address = view.findViewById(R.id.adress) as TextView
        val size = view.findViewById<TextView>(R.id.size)
        val item = getItem(position)
        if (item != null) {
            customerName.text = context.getString(R.string.format_name).format(item.customer.firstname, item.customer.firstname)
            address.text = context.getString(R.string.format_name).format(item.customer.address, item.customer.city)
            size.text = item.order.order.quantity.toString()
        }
        view.findViewById<CheckBox>(R.id.status).setOnCheckedChangeListener {
                _: CompoundButton, b: Boolean ->
            if (b){
                val data = getItem(position)?.order?.order
                if (data != null) {
                    data.status = "delivery"
                    CoroutineScope(Dispatchers.IO).launch {
                        model.update(data)
                    }
                }
            }
        }
        return view
    }
}