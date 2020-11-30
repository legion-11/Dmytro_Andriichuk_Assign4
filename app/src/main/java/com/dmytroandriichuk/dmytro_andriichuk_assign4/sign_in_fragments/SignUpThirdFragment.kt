package com.dmytroandriichuk.dmytro_andriichuk_assign4.sign_in_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.dmytroandriichuk.dmytro_andriichuk_assign4.R
import com.dmytroandriichuk.dmytro_andriichuk_assign4.SignUpActivity
import com.google.android.material.textfield.TextInputLayout

//confirm registration
class SignUpThirdFragment : Fragment() {

    lateinit var mactivity: SignUpActivity
    var address = ""
    var city = ""
    var post = ""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_sign_up_third, container, false)

        mactivity = activity as SignUpActivity

        root.findViewById<Button>(R.id.buttonBack3).setOnClickListener {
            val transaction = mactivity.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment, mactivity.fragmentSecond)
            transaction.commit()
        }

        root.findViewById<Button>(R.id.buttonCreateCustomer).setOnClickListener {
            if(checkInput(root)){ mactivity.createAdmin(it) }

        }
        return root
    }

    fun checkInput(root:View): Boolean{
        var returnable = true
        address = root.findViewById<EditText>(R.id.address_edit_text).text.toString()
        val address_err = root.findViewById<TextInputLayout>(R.id.addressInputLayout)
        city = root.findViewById<EditText>(R.id.city_edit_text).text.toString()
        val city_err = root.findViewById<TextInputLayout>(R.id.cityInputLayout)
        post = root.findViewById<EditText>(R.id.post_edit_text).text.toString()
        val post_err = root.findViewById<TextInputLayout>(R.id.postInputLayout)

        if(address.isEmpty()){
            address_err.error = "no address"
            returnable = false
        }else{address_err.error = ""}
        if(city.isEmpty()){
            city_err.error = "no city"
            returnable = false
        }else{city_err.error = ""}
        if(post.isEmpty()){
            post_err.error = "no post code"
            returnable = false
        }else{post_err.error = ""}
        return returnable
    }
}