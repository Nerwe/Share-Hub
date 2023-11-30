package com.example.sharehub

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sharehub.sharehub_db.Card
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){
    @Inject lateinit var cardListAdapter: CardListAdapter
    @Inject lateinit var cardViewModel: CardViewModel

    private val newCardActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intentData = result.data
            intentData?.getStringArrayListExtra(NewCardActivity.EXTRA_REPLY)?.let { replyArray ->
                if (replyArray.size == 5) {
                    val name = replyArray[0]
                    val surname = replyArray[1]
                    val father = replyArray[2]
                    val number = replyArray[3]
                    val cvv = replyArray[4]
                    val card = Card(name, surname, father, number, cvv)
                    cardViewModel.insert(card)
                }
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    /*private val cardViewModel: CardViewModel by viewModels {
        CardViewModelFactory((application as CardApplication).repository)
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        //val adapter = CardListAdapter()
        recyclerView.adapter = cardListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(
                this@MainActivity,
                NewCardActivity::class.java
            )
            newCardActivityLauncher.launch(intent)
        }

        val del = findViewById<FloatingActionButton>(R.id.del)
        del.setOnClickListener{
            cardViewModel.deleteAll()
        }

        cardViewModel.allCards.observe(this) { cards ->
            cards.let { cardListAdapter.submitList(it) }
        }
    }
}
