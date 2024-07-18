package com.example.amie_notetakingapplication

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.amie_notetakingapplication.databinding.ActivityRegistrationBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterationActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegistrationBinding
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.LoginRedirectBtn.setOnClickListener(){
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.backButton.setOnClickListener(){
            finish()
        }

        firebaseAuth = FirebaseAuth.getInstance()

        binding.RegisterPageBtn.setOnClickListener(){
            val name = binding.outlinedTextFieldName.editText?.text.toString()
            val email = binding.outlinedTextFieldEmail.editText?.text.toString()
            val password = binding.outlinedTextFieldPassword.editText?.text.toString()
            val confirmPassword = binding.outlinedTextFieldConfirmPassword.editText?.text.toString()

            if (name.isNotBlank() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
                if(password == confirmPassword){
                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(){
                        if (it.isSuccessful){
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Make Sure Password is Same", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show()
            }

        }
    }
}