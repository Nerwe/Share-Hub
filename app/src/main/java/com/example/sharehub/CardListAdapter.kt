package com.example.sharehub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sharehub.sharehub_db.Card
import javax.inject.Inject


class CardListAdapter @Inject constructor() : ListAdapter<Card, CardListAdapter.CardViewHolder>(CARDS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(
            current.name,
            current.surname,
            current.father,
            current.number,
            current.cvv)
    }

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardNameItemView: TextView = itemView.findViewById(R.id.nameText)
        private val cardSurnameItemView: TextView = itemView.findViewById(R.id.surnameText)
        private val cardFatherItemView: TextView = itemView.findViewById(R.id.fatherText)
        private val cardNumberItemView: TextView = itemView.findViewById(R.id.numberText)
        private val cardCVVItemView: TextView = itemView.findViewById(R.id.cvvText)

        fun bind(
            name: String?,
            surname: String?,
            father: String?,
            number: String?,
            cvv: String?
        ) {
            cardNameItemView.text = name
            cardSurnameItemView.text = surname
            cardFatherItemView.text = father
            cardNumberItemView.text = number
            cardCVVItemView.text = cvv
        }

        companion object {
            fun create(parent: ViewGroup): CardViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return CardViewHolder(view)
            }
        }
    }

    companion object {
        private val CARDS_COMPARATOR = object : DiffUtil.ItemCallback<Card>() {
            override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
                return oldItem.number == newItem.number
            }
        }
    }
}