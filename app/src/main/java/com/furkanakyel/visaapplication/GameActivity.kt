package com.furkanakyel.visaapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    private val fruits = listOf("elma", "armut", "muz", "karpuz", "kiraz", "portakal", "şeftali", "mandalina", "nar", "ananas", "çilek", "mango")
    private var currentFruit: String = ""
    private var score: Int = 0

    private lateinit var txvScrambledFruit: TextView
    private lateinit var etGuessFruit: EditText
    private lateinit var txvScore: TextView
    private lateinit var btnSubmitGuess: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        txvScrambledFruit = findViewById(R.id.txvScrambledFruit)
        etGuessFruit = findViewById(R.id.etGuessFruit)
        txvScore = findViewById(R.id.txvScore)
        btnSubmitGuess = findViewById(R.id.btnSubmitGuess)

        pickAndScrambleFruit()

        btnSubmitGuess.setOnClickListener {
            checkGuess()
        }
    }

    private fun pickAndScrambleFruit() {
        currentFruit = fruits.random()

        val scrambledFruit = currentFruit.toCharArray().apply { shuffle() }.concatToString()

        txvScrambledFruit.text = scrambledFruit
    }

    private fun checkGuess() {
        val userGuess = etGuessFruit.text.toString().trim().toLowerCase()

        if (userGuess == currentFruit) {
            score += 10
            Toast.makeText(this, "Doğru Tahmin! 10 Puan Kazandınız.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Yanlış Tahmin. Tekrar deneyin.", Toast.LENGTH_SHORT).show()
        }

        txvScore.text = "Puan: $score"

        pickAndScrambleFruit()

        etGuessFruit.text.clear()
    }
}
