package com.example.amie_notetakingapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.amie_notetakingapplication.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.RegisterRedirectBtn.setOnClickListener(){
            intent = Intent(this, RegisterationActivity::class.java)
            startActivity(intent)
        }
        binding.backButton.setOnClickListener(){
            finish()
        }

        firebaseAuth = FirebaseAuth.getInstance()

        binding.LoginPageBtn.setOnClickListener(){
            val email = binding.outlinedTextFieldEmail.editText?.text.toString()
            val password = binding.outlinedTextFieldPassword.editText?.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(){
                    if (it.isSuccessful){
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Email and Password is Incorrect", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


    }
}