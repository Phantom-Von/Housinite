package com.example.housinite

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.housinite.R
import com.google.firebase.database.FirebaseDatabase

class OwnersSignUp : AppCompatActivity() {
    lateinit var ownerEdtName: EditText
    lateinit var ownerEdtEmail: EditText
    lateinit var ownerEdtPhoneNumber: EditText
    private lateinit var ownerEdtPassword: EditText
    private lateinit var ownerEdtConfirmPassword: EditText
    lateinit var obtnSave: Button
    lateinit var obtnView: Button
    lateinit var tvSignIn: TextView
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_owners_sign_up)
        ownerEdtName = findViewById(R.id.ownerName)
        ownerEdtEmail = findViewById(R.id.ownerEmail)
        ownerEdtPhoneNumber = findViewById(R.id.ownerPhone)
        ownerEdtPassword = findViewById(R.id.ownerPassword)
        ownerEdtConfirmPassword = findViewById(R.id.ownerConfirmPassword)
        obtnSave = findViewById(R.id.obtnSave)
        obtnView = findViewById(R.id.obtnView)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Saving")
        progressDialog.setMessage("Please wait...")
        obtnSave.setOnClickListener {
            var name = ownerEdtName.text.toString().trim()
            var email = ownerEdtEmail.text.toString().trim()
            var phoneNumber = ownerEdtPhoneNumber.text.toString().trim()
            var password = ownerEdtPassword.text.toString().trim()
            var confirmPassword = ownerEdtConfirmPassword.text.toString().trim()
            var id = System.currentTimeMillis().toString()
            // Check if user is submitting empty fields
            if (name.isEmpty()) {
                ownerEdtName.setError("Please fill this input")
                ownerEdtName.requestFocus()
            }else if (email.isEmpty()){
                ownerEdtEmail.setError("Please fill this input")
                ownerEdtEmail.requestFocus()
            }else if (phoneNumber.isEmpty()){
                ownerEdtPhoneNumber.setError("Please fill this input")
                ownerEdtPhoneNumber.requestFocus()
            }else if (phoneNumber.isEmpty()){
                ownerEdtPassword.setError("Please fill this input")
                ownerEdtPassword.requestFocus()
            }else if (phoneNumber.isEmpty()){
                ownerEdtConfirmPassword.setError("Please fill this input")
                ownerEdtConfirmPassword.requestFocus()
            }else{
                // Proceed to save
                var owner = Owners(name, email, phoneNumber, password, confirmPassword, id)
                // Create a reference to the Firebase
                var ref = FirebaseDatabase.getInstance().getReference().child("Owners/"+id)
                progressDialog.show()
                ref.setValue(owner).addOnCompleteListener {
                    progressDialog.dismiss()
                    if (it.isSuccessful){
                        Toast.makeText(this,"Owner saved successfully!", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this,"Owner saving failed!", Toast.LENGTH_LONG).show()
                    }
                }
            }

        }
        obtnView.setOnClickListener {
            var intent = Intent(this,OwnersActivity::class.java)
            startActivity(intent)
        }

        tvSignIn.setOnClickListener {
            startActivity(Intent(this@OwnersSignUp,OwnersLogin::class.java))

        }

    }
}