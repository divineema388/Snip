package com.snip.dealabs

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

// Change the list type to SnippetItem
class SnippetAdapter(private val snippets: MutableList<SnippetItem>) :
    RecyclerView.Adapter<SnippetViewHolder>() {

    // Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnippetViewHolder {
        // Create the view for a single snippet item using the static method from SnippetViewHolder
        val itemView = SnippetViewHolder.createView(parent)
        return SnippetViewHolder(itemView)
    }

    // Called by RecyclerView to display the data at the specified position.
    // This method updates the contents of the itemView to reflect the item at the given position.
    override fun onBindViewHolder(holder: SnippetViewHolder, position: Int) {
        val snippetItem = snippets[position] // Get the SnippetItem at this position
        holder.bind(snippetItem) // Pass the SnippetItem to the ViewHolder's bind method
    }

    // Returns the total number of items in the data set held by the adapter.
    override fun getItemCount(): Int {
        return snippets.size
    }
}