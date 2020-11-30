package com.dmytroandriichuk.dmytro_andriichuk_assign4.sign_in_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.dmytroandriichuk.dmytro_andriichuk_assign4.R
import com.dmytroandriichuk.dmytro_andriichuk_assign4.SignUpActivity
import com.dmytroandriichuk.dmytro_andriichuk_assign4.models.UsersModel
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//first frame for registration
class SignUpInitFragment : Fragment() {

    var username: String = ""
    var password: String = ""
    lateinit var userInputError: TextInputLayout
    lateinit var passwordInputError: TextInputLayout
    lateinit var password2InputError: TextInputLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_sign_up_init, container, false)
        val mactivity = activity as SignUpActivity



        val userInput = root.findViewById<EditText>(R.id.usernameInput)
        val passwordInput = root.findViewById<EditText>(R.id.passwordInput)
        val password2Input = root.findViewById<EditText>(R.id.passwordInput2)
        userInputError = root.findViewById(R.id.usernameInputLayout)
        passwordInputError = root.findViewById(R.id.passwordInputLayout)
        password2InputError = root.findViewById(R.id.passwordInputLayout2)

        root.findViewById<Button>(R.id.buttonNext).setOnClickListener {
            val usernameText = userInput.text.toString()
            val passwordText = passwordInput.text.toString()
            val password2Text = password2Input.text.toString()

            if (checkInput(usernameText, passwordText, password2Text)){
                CoroutineScope(Dispatchers.IO).launch{
                    val check = mactivity.usersModel.checkUsername(usernameText)
                    withContext(Dispatchers.Main){
                        if ( check == 0 ) {
                            username = usernameText
                            password = passwordText
                            val transaction = mactivity.supportFragmentManager.beginTransaction()
                            transaction.replace(R.id.fragment, mactivity.fragmentSecond).commit()
                        } else { userInputError.error = getString(R.string.uwername_error) }
                    }
                }
            }
        }

        root.findViewById<Button>(R.id.buttonBack).setOnClickListener{ mactivity.finish() }

        return root
    }

    private fun checkInput(username: String, password: String, password2: String): Boolean {
        return when {
            password != password2 -> {
                passwordInputError.error = getString(R.string.password_error)
                false
            }
            password.length < 6 -> {
                passwordInputError.error = getString(R.string.password_error_2)
                false
            }
            username.isEmpty() -> {
                userInputError.error = getString(R.string.username_error)
                false
            }
            else -> {
                passwordInputError.error = ""
                userInputError.error = ""
                true
            }
        }
    }
}