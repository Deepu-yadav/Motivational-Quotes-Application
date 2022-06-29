package com.example.quotesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuotesListAdapter(val context:Context,val list:List<QuotesResponse>,val listener:CopyListner)
    :RecyclerView.Adapter<QuotesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        val layout=LayoutInflater.from(context).inflate(R.layout.list_quotes,parent,false)
        return QuotesViewHolder(layout)
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        holder.textview_quotes.text=list.get(position).text
        holder.textview_author.text=list.get(position).text
        holder.button_copy.setOnClickListener{
            listener.onCopyClicked(list.get(holder.adapterPosition).text)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}

class QuotesViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

    var textview_quotes:TextView=itemView.findViewById(R.id.textview_quotes)
    var textview_author:TextView=itemView.findViewById(R.id.textview_author)
    var button_copy:Button=itemView.findViewById(R.id.button_copy)
}