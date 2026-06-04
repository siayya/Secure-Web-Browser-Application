package com.example.securewebbrowserapplication.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.securewebbrowserapplication.Adapter.SliderAdapter
import com.example.securewebbrowserapplication.R
import com.example.securewebbrowserapplication.databinding.ActivityHomeScreenBinding
import com.example.securewebbrowserapplication.model.SliderItem
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var googleSignInClient: GoogleSignInClient
        super.onCreate(savedInstanceState)
        var binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val sliderItems = listOf(
            SliderItem(
                R.drawable.slider1,
                "Browse Securely",
                "Protect your privacy while surfing the web."
            ),
            SliderItem(
                R.drawable.slider2,
                "Fast Experience",
                "Enjoy lightning-fast browsing performance."
            ),
            SliderItem(
                R.drawable.slider3,
                "History Tracking",
                "View and manage your browsing history."
            )
        )

        binding.viewPage.adapter = SliderAdapter(sliderItems)

        binding.dotsIndicator.attachTo(binding.viewPage)

        binding.open.setOnClickListener {

            var url = binding.etUrl.text.toString().trim()

            if (url.isEmpty()) {
                Toast.makeText(
                    this,
                    "Please enter a URL",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (!url.startsWith("http://") &&
                !url.startsWith("https://")
            ) {
                url = "https://$url"
            }

            val intent = Intent(
                this,
                WebViewScreen::class.java
            )

            intent.putExtra("URL", url)

            startActivity(intent)
        }

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

        binding.historyIc.setOnClickListener {

            val intent = Intent(
                this,
                HistoryScreen::class.java
            )

            startActivity(intent)
        }

        binding.logoutBt.setOnClickListener {

            FirebaseAuth.getInstance().signOut()

            googleSignInClient.signOut()
                .addOnCompleteListener {

                    val intent = Intent(
                        this,
                        SignInScreen::class.java
                    )

                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or
                                Intent.FLAG_ACTIVITY_CLEAR_TASK

                    startActivity(intent)
                    finish()
                }
        }


    }
}