package com.example.miles.whotextsfirst.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.miles.whotextsfirst.R
import com.example.miles.whotextsfirst.model.Conversation
import kotlinx.android.synthetic.main.conversation_view_holder.view.*

class ConversationsAdapter : RecyclerView.Adapter<ConversationsAdapter.ConversationViewHolder>() {

    private var conversations = listOf<Conversation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationViewHolder {
        return ConversationViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.conversation_view_holder, parent, false)
        )
    }

    override fun getItemCount() = conversations.size

    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {
        holder.setupForConversation(conversations[position])
    }

    fun loadConversations(conversations: List<Conversation>) {
        this.conversations = conversations
        notifyDataSetChanged()
    }

    inner class ConversationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setupForConversation(conversation: Conversation) {
            itemView.correspondent_name_text_view.text = conversation.correspondentName

            itemView.correspondent_percent_text_view.text = conversation.correspondentPercent.toString() + '%'
            itemView.you_percent_text_view.text = (100 - conversation.correspondentPercent).toString() + '%'

            with(itemView.divider.layoutParams as ConstraintLayout.LayoutParams) {
                horizontalBias = (conversation.correspondentPercent / 100.0).toFloat()
            }
        }
    }
}
