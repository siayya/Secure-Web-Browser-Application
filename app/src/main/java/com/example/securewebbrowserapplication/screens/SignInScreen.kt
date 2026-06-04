package com.example.securewebbrowserapplication.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.securewebbrowserapplication.R
import com.example.securewebbrowserapplication.databinding.ActivitySignInScreenBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth

class SignInScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySignInScreenBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    private val RC_SIGN_IN = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        )
            .requestEmail()
            .build()

        googleSignInClient =
            GoogleSignIn.getClient(
                this,
                gso
            )

        binding.google.setOnClickListener {

            val signInIntent = googleSignInClient.signInIntent

            startActivityForResult(
                signInIntent,
                RC_SIGN_IN
            )
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(
            requestCode,
            resultCode,
            data
        )

        if (requestCode == RC_SIGN_IN) {

            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)

            try {

                val account =
                    task.getResult(ApiException::class.java)

                Toast.makeText(
                    this,
                    "Signed in as: ${account.email}",
                    Toast.LENGTH_LONG
                ).show()

                startActivity(
                    Intent(
                        this,
                        HomeScreen::class.java
                    )
                )

            } catch (e: ApiException) {

                Toast.makeText(
                    this,
                    "Error Code: ${e.statusCode}",
                    Toast.LENGTH_LONG
                ).show()

                e.printStackTrace()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        if (FirebaseAuth.getInstance().currentUser != null) {

            startActivity(
                Intent(
                    this,
                    HomeScreen::class.java
                )
            )

            finish()
        }
    }
}