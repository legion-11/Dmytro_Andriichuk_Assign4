package com.dmytroandriichuk.dmytro_andriichuk_assign4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*


//customers screen to place order
class PlaceOrderActivity : AppCompatActivity() {
    var priceCount = 0.0
    lateinit var spinner: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_order)
        spinner = findViewById<Spinner>(R.id.pizzaType)

        spinner.adapter = ArrayAdapter<CharSequence>(this, R.layout.spinner_item,
            resources.getStringArray(R.array.pizzas))
    }

    fun confirmOrder(v: View){
        val radioGroup = findViewById<RadioGroup>(R.id.sizesRadioGroup)
//      save size
        priceCount = 0.0
        val size = when (radioGroup.checkedRadioButtonId){
            R.id.radioButtonSmall -> {
                priceCount +=12
                "Small"
            }
            R.id.radioButtonMedium -> {
                priceCount +=15
                "Medium"
            }
            R.id.radioButtonLarge -> {
                priceCount +=18
                "Large"
            }
            else -> {priceCount +=25
                "Extra large"
            }
        }
        val intent = Intent(this@PlaceOrderActivity, OrderInformation::class.java).apply {
            putExtra("typeOfPizza", spinner.selectedItem.toString())
            putExtra("size", size)
            putExtras(intent)
        }

        val checkBoxIdArray = arrayOf(R.id.checkBox1, R.id.checkBox2, R.id.checkBox3,
            R.id.checkBox4, R.id.checkBox5, R.id.checkBox6)

//      unite all toppings in one string
        var toppings = ""
        for (id in checkBoxIdArray){
            val currentCheckBox = findViewById<CheckBox>(id)
            if (currentCheckBox.isChecked){
                toppings += currentCheckBox.text.toString() + " "
                priceCount += 1
            }
        }
        intent.putExtra("toppings", toppings)
        intent.putExtra("price", priceCount)
        startActivity(intent)
    }

    fun changeUserData(v: View){
        val id = intent.getIntExtra("id", -1)
        val intent = Intent(this@PlaceOrderActivity, ChangeNameActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}
