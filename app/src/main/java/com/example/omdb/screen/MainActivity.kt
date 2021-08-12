package com.example.omdb.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.omdb.R

class MainActivity : AppCompatActivity() {
    lateinit var searchText: EditText
    private lateinit var searchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchText = findViewById(R.id.etv_search)
        searchButton = findViewById(R.id.ivb_btn_search)

        searchText.text = null

        searchButton.setOnClickListener {
            if (!searchText.text.isNullOrBlank()){
                var intent = Intent(this, MovieList::class.java)
                intent.putExtra("movieName", searchText.text.toString())
                startActivity(intent)
            }else{
                Toast.makeText(this, "Please enter movie name...", Toast.LENGTH_SHORT).show()
            }
        }
    }
}