package com.dmytroandriichuk.dmytro_andriichuk_assign4.sign_in_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.dmytroandriichuk.dmytro_andriichuk_assign4.R
import com.dmytroandriichuk.dmytro_andriichuk_assign4.SignUpActivity
import com.dmytroandriichuk.dmytro_andriichuk_assign4.dbTables.Admin
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//second frame for registration
class SignUpSecondFragment : Fragment() {
    var lastName = ""
    var firstName = ""
    lateinit var firstNameLayout: TextInputLayout
    lateinit var lastNameLayout: TextInputLayout
    lateinit var firstNameEditText: EditText
    lateinit var lastNameEditText : EditText
    lateinit var mactivity: SignUpActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_sign_up_second, container, false)
        mactivity = activity as SignUpActivity

        root.findViewById<Button>(R.id.buttonBack2).setOnClickListener {
            val transaction = mactivity.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment, mactivity.fragmentInit)
            transaction.commit()
        }

        firstNameEditText = root.findViewById(R.id.firstInput)
        lastNameEditText = root.findViewById(R.id.lastInput)
        firstNameLayout = root.findViewById(R.id.firstInputLayout)
        lastNameLayout = root.findViewById(R.id.lastInputLayout)

        root.findViewById<Button>(R.id.createEmploee).setOnClickListener{
            if (showError()){ mactivity.createAdmin(it) }
        }
        root.findViewById<Button>(R.id.buttonBack2).setOnClickListener{
            val transaction = mactivity.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment, mactivity.fragmentInit)
            transaction.commit()
        }
        root.findViewById<Button>(R.id.buttonNext2).setOnClickListener{
            if (showError()){
                val transaction = mactivity.supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment, mactivity.fragmentThird)
                transaction.commit()
            }
        }
        return root
    }

    private fun showError() : Boolean{
        var returner = true
        firstName = firstNameEditText.text.toString()
        lastName = lastNameEditText.text.toString()
        if (firstName.isEmpty()) {
            returner = false
            firstNameLayout.error = "first name is empty"
        }else{firstNameLayout.error = ""}
        if (lastNameEditText.text.toString().isEmpty()) {
            returner = false
            lastNameLayout.error = "last name is empty"
        }else{lastNameLayout.error = ""}
        return returner
    }

}