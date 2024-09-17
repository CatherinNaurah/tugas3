package com.example.tugas3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Variable untuk melacak EditText yang aktif
    private var activeEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nilai1 = findViewById<EditText>(R.id.editText1)
        val nilai2 = findViewById<EditText>(R.id.editText2)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val buttonCalculate = findViewById<Button>(R.id.buttonHitung)

        // Set EditText yang aktif saat user klik salah satu EditText
        nilai1.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) activeEditText = nilai1
        }
        nilai2.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) activeEditText = nilai2
        }

        // Listener untuk tombol angka
        val buttons = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6,
            R.id.button7, R.id.button8, R.id.button9
        )

        for (buttonId in buttons) {
            findViewById<Button>(buttonId).setOnClickListener { view ->
                val button = view as Button
                activeEditText?.append(button.text)
            }
        }

        buttonCalculate.setOnClickListener {
            val num1 = nilai1.text.toString().toDoubleOrNull()
            val num2 = nilai2.text.toString().toDoubleOrNull()

            if (num1 == null || num2 == null) {
                Toast.makeText(this, "Masukkan angka dengan benar", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val result = when (radioGroup.checkedRadioButtonId) {
                R.id.radioButtonTambah -> num1 + num2
                R.id.radioButtonKurang -> num1 - num2
                R.id.radioButtonKali -> num1 * num2
                R.id.radioButtonBagi -> {
                    if (num2 == 0.0) {
                        Toast.makeText(this, "Tidak bisa dibagi dengan nol", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    num1 / num2
                }
                else -> {
                    Toast.makeText(this, "Pilih operasi", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            // Kirim hasil, nama, dan NIM ke ResultActivity
            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra("RESULT", result)
                putExtra("NAME", "Catherin Naurah Erdina")
                putExtra("NIM", "225150407111084")
            }
            startActivity(intent)
        }
    }
}
