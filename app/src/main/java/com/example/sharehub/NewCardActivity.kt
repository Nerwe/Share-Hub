package com.example.sharehub

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class NewCardActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_card)
        val nameCardView = findViewById<EditText>(R.id.name_card)
        val surnameCardView = findViewById<EditText>(R.id.surname_card)
        val fatherCardView = findViewById<EditText>(R.id.father_card)
        val numberCardView = findViewById<EditText>(R.id.number_card)
        val cvvCardView = findViewById<EditText>(R.id.cvv_card)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(nameCardView.text) ||
                TextUtils.isEmpty(surnameCardView.text) ||
                TextUtils.isEmpty(fatherCardView.text) ||
                TextUtils.isEmpty(numberCardView.text) ||
                TextUtils.isEmpty(cvvCardView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val nameCardText = nameCardView.text.toString()
                val surnameCardText = surnameCardView.text.toString()
                val fatherCardText = fatherCardView.text.toString()
                val numberCardText = numberCardView.text.toString()
                val cvvCardText = cvvCardView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, arrayListOf(
                    nameCardText,
                    surnameCardText,
                    fatherCardText,
                    numberCardText,
                    cvvCardText))

                //setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.cardlistsql.REPLY"
    }
}