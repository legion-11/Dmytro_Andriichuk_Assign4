package com.dmytroandriichuk.dmytro_andriichuk_assign4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.Admin
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.Customer
import com.dmytroandriichuk.dmytro_andriichuk_assign4.models.UsersModel
import com.dmytroandriichuk.dmytro_andriichuk_assign4.sign_in_fragments.*

//have three fragments to complete registration
class SignUpActivity : AppCompatActivity() {
    val fragmentInit = SignUpInitFragment()
    val fragmentSecond = SignUpSecondFragment()
    val fragmentThird = SignUpThirdFragment()

    lateinit var usersModel: UsersModel
    lateinit var fragment: FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        usersModel = ViewModelProviders.of(this).get(UsersModel::class.java)
        fragment = findViewById(R.id.fragment)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment, fragmentInit)
        transaction.commit()
    }

    fun createAdmin(v: View){
        val username = fragmentInit.username
        val password = fragmentInit.password
        val firstName = fragmentSecond.firstName
        val lastName = fragmentSecond.lastName
        if (v.id == R.id.createEmploee){
            val admin = Admin(0, username, password, firstName, lastName)
            usersModel.insert(admin)
        } else {
            val address = fragmentThird.address
            val city = fragmentThird.city
            val post = fragmentThird.post
            val customer = Customer(0, username, password, firstName, lastName, address, city, post)
            usersModel.insert(customer)
        }
        Toast.makeText(this, "user account created", Toast.LENGTH_LONG).show()
        finish()
    }
}