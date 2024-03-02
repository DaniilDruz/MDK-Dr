package ru.netology.nmedia.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import ru.netology.nmedia.R

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            var myIntent = Intent(this, MainActivity::class.java)
            startActivity((myIntent))
        }
    }

}