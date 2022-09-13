package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btn_login : Button
    private lateinit var btn_signup: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()

        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        btn_login = findViewById(R.id.btn_login)
        btn_signup = findViewById(R.id.btn_signup)

        btn_signup.setOnClickListener{
            val intent = Intent(this, Signup::class.java)
            finish()
            startActivity(intent)
        }

        btn_login.setOnClickListener{
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            login(email,password)
        }

    }

    private fun login(email: String, password: String){
        //To Log In:
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Code to log in user
                    val intent = Intent(this@Login, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this@Login,"User Does not Exist", Toast.LENGTH_SHORT).show()
                }
            }


    }
}