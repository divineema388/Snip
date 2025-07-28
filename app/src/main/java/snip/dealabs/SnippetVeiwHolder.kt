package com.snip.dealabs

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import android.view.View.generateViewId // Import generateViewId

class SnippetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val originalSnippetTextView: TextView
    private val replaceWithLabel: TextView
    private val replacementSnippetTextView: TextView
    private val replaceWithSection: LinearLayout // The container for "Replace with" label and snippet

    init {
        // Find the TextViews and LinearLayout by their generated IDs
        originalSnippetTextView = itemView.findViewById(GeneratedViewIds.ORIGINAL_SNIPPET_TEXT_VIEW_ID)
        replaceWithLabel = itemView.findViewById(GeneratedViewIds.REPLACE_WITH_LABEL_ID)
        replacementSnippetTextView = itemView.findViewById(GeneratedViewIds.REPLACEMENT_SNIPPET_TEXT_VIEW_ID)
        replaceWithSection = itemView.findViewById(GeneratedViewIds.REPLACE_WITH_SECTION_ID)

        // Set click listeners for copying to clipboard
        originalSnippetTextView.setOnClickListener {
            copyTextToClipboard(originalSnippetTextView.text.toString())
        }
        replacementSnippetTextView.setOnClickListener {
            copyTextToClipboard(replacementSnippetTextView.text.toString())
        }
    }

    private fun copyTextToClipboard(text: String) {
        val context = itemView.context
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Code Snippet", text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Copied to clipboard!", Toast.LENGTH_SHORT).show()
    }

    companion object {
        // Define unique IDs for our programmatically created views
        // Using generateViewId() at runtime is safer for dynamically created views
        // but here, we're simulating fixed IDs for simplicity of findViewById.
        // For production, consider a custom R.id generation strategy or view binding.
        // Or simply define these in ids.xml if you want static R.id.
        object GeneratedViewIds {
            val ORIGINAL_SNIPPET_TEXT_VIEW_ID = generateViewId()
            val REPLACE_WITH_LABEL_ID = generateViewId()
            val REPLACEMENT_SNIPPET_TEXT_VIEW_ID = generateViewId()
            val REPLACE_WITH_SECTION_ID = generateViewId()
        }

        fun createView(parent: ViewGroup): View {
            val context = parent.context

            // Root LinearLayout for a single snippet item (the card)
            val itemLayout = LinearLayout(context).apply {
                layoutParams = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    bottomMargin = 24 // More margin between items
                }
                orientation = LinearLayout.VERTICAL
                setBackgroundColor(Color.parseColor("#E0E0E0")) // Light grey background for item
                setPadding(16, 16, 16, 16) // Padding inside the item
                elevation = 4f // Add a subtle shadow
                clipToPadding = false // Allows children to draw outside padding
                clipChildren = false // Allows children to draw outside bounds
            }

            // TextView for the original code snippet
            val originalSnippetTextView = TextView(context).apply {
                id = GeneratedViewIds.ORIGINAL_SNIPPET_TEXT_VIEW_ID
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    bottomMargin = 16 // Space between original snippet and 'Replace with'
                }
                setTextColor(Color.parseColor("#4CAF50")) // Green color for font
                textSize = 14f
                typeface = Typeface.MONOSPACE // Monospace font for code
                gravity = Gravity.TOP or Gravity.START
                setBackgroundColor(Color.parseColor("#212121")) // Dark background for code block
                setPadding(12, 12, 12, 12)
            }
            itemLayout.addView(originalSnippetTextView)

            // LinearLayout for the "Replace with" section (label + replacement snippet)
            val replaceWithSection = LinearLayout(context).apply {
                id = GeneratedViewIds.REPLACE_WITH_SECTION_ID
                orientation = LinearLayout.VERTICAL
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                visibility = View.GONE // Initially hidden, will be shown if replacement exists
            }
            itemLayout.addView(replaceWithSection)

            // "Replace with" Label
            val replaceWithLabel = TextView(context).apply {
                id = GeneratedViewIds.REPLACE_WITH_LABEL_ID
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    bottomMargin = 8 // Space between label and replacement snippet
                }
                text = "Replace with:"
                setTextColor(Color.GRAY) // A subtle color for the label
                textSize = 12f
                typeface = Typeface.DEFAULT_BOLD
            }
            replaceWithSection.addView(replaceWithLabel)

            // TextView for the replacement code snippet
            val replacementSnippetTextView = TextView(context).apply {
                id = GeneratedViewIds.REPLACEMENT_SNIPPET_TEXT_VIEW_ID
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                setTextColor(Color.parseColor("#4CAF50")) // Green color for font
                textSize = 14f
                typeface = Typeface.MONOSPACE // Monospace font for code
                gravity = Gravity.TOP or Gravity.START
                setBackgroundColor(Color.parseColor("#212121")) // Dark background for code block
                setPadding(12, 12, 12, 12)
            }
            replaceWithSection.addView(replacementSnippetTextView)

            return itemLayout
        }
    }

    // Method to bind the data (SnippetItem) to the views in this holder
    fun bind(snippetItem: SnippetItem) {
        originalSnippetTextView.text = snippetItem.originalSnippet

        // Show/hide the replacement section based on whether a replacement exists
        if (snippetItem.replacementSnippet != null) {
            replaceWithSection.visibility = View.VISIBLE
            replacementSnippetTextView.text = snippetItem.replacementSnippet
        } else {
            replaceWithSection.visibility = View.GONE
        }
    }
}