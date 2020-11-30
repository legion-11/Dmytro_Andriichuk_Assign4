package com.dmytroandriichuk.dmytro_andriichuk_assign4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.dmytroandriichuk.dmytro_andriichuk_assign4.models.UsersModel
import com.google.android.material.textfield.TextInputLayout


import kotlinx.coroutines.*

val TAG = "MainActivity.tag"

//sign in screen
class MainActivity : AppCompatActivity() {
    lateinit var usersModel: UsersModel
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usersModel = ViewModelProvider(this).get(UsersModel::class.java)
        sharedPreferences = getSharedPreferences("userInput", Context.MODE_PRIVATE)

        val checkbox = findViewById<CheckBox>(R.id.emploeeCheck)
        val username = findViewById<EditText>(R.id.usernameInput)
        val password = findViewById<EditText>(R.id.passwordInput)

        checkbox.setOnClickListener {
            username.setText(sharedPreferences.getString( resources.getString(if (!checkbox.isChecked) R.string.shared_username else R.string.shared_username_admin) , ""))
            password.setText(sharedPreferences.getString( resources.getString(if (!checkbox.isChecked) R.string.shared_password else R.string.shared_password_admin) , ""))
        }
        checkbox.isChecked = sharedPreferences.getBoolean(resources.getString(R.string.shared_checkbox), false)
        checkbox.callOnClick()

        findViewById<Button>(R.id.buttonLogin).setOnClickListener {
            signIn(username.text.toString(), password.text.toString(), checkbox.isChecked)
        }

        findViewById<Button>(R.id.buttonSignUp).setOnClickListener {
            intent = Intent(this@MainActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signIn(username: String, password: String, checked: Boolean){
        CoroutineScope(Dispatchers.IO ).launch {
            if (!checked) {
                val customer = usersModel.getCustomer(username, password)
                //change thread to change ui
                withContext(Dispatchers.Main) {
                    if (checkFound(customer != null) && customer != null) {
                        saveSharedPrefferences(username, password, checked)
                        intent = Intent(this@MainActivity, PlaceOrderActivity::class.java)
                        intent.putExtra("id", customer.customerId)
                        startActivity(intent)
                    }
                }
            } else {
                val admin = usersModel.getAdmin(username, password)
                //change thread to change ui
                withContext(Dispatchers.Main) {
                    if (checkFound(admin != null) && admin != null) {
                        saveSharedPrefferences(username, password, checked)
                        intent = Intent(this@MainActivity, SeeOrdersActivity::class.java)
                        intent.putExtra("id", admin.employeeId)
                        startActivity(intent)
                    }
                }
            }
        }
    }

    // show errors on layoutinput
    fun checkFound(resultSuccess: Boolean): Boolean {
        if (resultSuccess){
            findViewById<TextInputLayout>(R.id.passwordInputLayout2).error = ""
            findViewById<TextInputLayout>(R.id.usernameInputLayout).error = ""
        } else {
            findViewById<TextInputLayout>(R.id.passwordInputLayout2).error = "Not found"
            findViewById<TextInputLayout>(R.id.usernameInputLayout).error = " "
        }
        return resultSuccess
    }

    fun saveSharedPrefferences(username: String, password: String, checked: Boolean){
        sharedPreferences.edit().apply {
            putBoolean(resources.getString(R.string.shared_checkbox), checked)
            putString(resources.getString(if (!checked) R.string.shared_username else R.string.shared_username_admin),  username)
            putString(resources.getString(if (!checked) R.string.shared_password else R.string.shared_password_admin),  password)
        }.apply()
    }
}