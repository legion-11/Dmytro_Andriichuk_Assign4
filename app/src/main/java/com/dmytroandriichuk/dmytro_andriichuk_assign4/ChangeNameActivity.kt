package com.dmytroandriichuk.dmytro_andriichuk_assign4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.Customer
import com.dmytroandriichuk.dmytro_andriichuk_assign4.models.UsersModel
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//change customer data
class ChangeNameActivity : AppCompatActivity() {
    lateinit var usersModel: UsersModel
    lateinit var customer: Customer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_name)

        usersModel = ViewModelProviders.of(this).get(UsersModel::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            customer = usersModel.getCustomer(intent.getIntExtra("id", -1))
            withContext(Dispatchers.Main){
                if (customer != null){
                    findViewById<EditText>(R.id.changelastName).setText(customer.lastname.toString())
                    findViewById<EditText>(R.id.changeName).setText(customer.firstname.toString())
                    findViewById<EditText>(R.id.address_edit_text).setText(customer.address)
                    findViewById<EditText>(R.id.city_edit_text).setText(customer.city)
                    findViewById<EditText>(R.id.post_edit_text).setText(customer.postalCode)
                }
            }
        }

        usersModel = ViewModelProviders.of(this).get(UsersModel::class.java)
        findViewById<Button>(R.id.buttonCreateCustomer).setOnClickListener {
            showError()
        }

        findViewById<Button>(R.id.buttonBack3).setOnClickListener {
            finish()
        }
    }

    private fun showError() {
        var returner = true
        val firstNameEditText = findViewById<EditText>(R.id.changeName)
        val firstName = firstNameEditText.text.toString()
        val lastNameEditText = findViewById<EditText>(R.id.changelastName)
        val lastName = lastNameEditText.text.toString()
        val nameL = findViewById<TextInputLayout>(R.id.textInputLayoutName)

        val lastNameL = findViewById<TextInputLayout>(R.id.textInputLLastNameLayout)

        if (firstName.isEmpty()) {
            returner = false
            nameL.error = "first name is empty"
        }else{nameL.error = ""}
        if (lastName.isEmpty()) {
            returner = false
            lastNameL.error = "last name is empty"
        }else{lastNameL.error = ""}

        val address = findViewById<EditText>(R.id.address_edit_text).text.toString()
        val address_err = findViewById<TextInputLayout>(R.id.addressInputLayout)
        val city = findViewById<EditText>(R.id.city_edit_text).text.toString()
        val city_err = findViewById<TextInputLayout>(R.id.cityInputLayout)
        val post = findViewById<EditText>(R.id.post_edit_text).text.toString()
        val post_err = findViewById<TextInputLayout>(R.id.postInputLayout)

        if(address.isEmpty()){
            address_err.error = "no address"
            returner = false
        }else{address_err.error = ""}
        if(city.isEmpty()){
            city_err.error = "no city"
            returner = false
        }else{city_err.error = ""}
        if(post.isEmpty()){
            post_err.error = "no post code"
            returner = false
        }else{post_err.error = ""}
        if (returner){
            CoroutineScope(Dispatchers.IO).launch {
                usersModel.update(Customer(
                    customer.customerId, customer.userName,
                    customer.password,
                firstName, lastName, address, city, post))
            }
            finish()
        }
    }
}