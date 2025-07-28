package com.snip.dealabs

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var snippetAdapter: SnippetAdapter
    // Change to hold SnippetItem objects
    private val snippets = mutableListOf<SnippetItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create the root layout (Vertical LinearLayout)
        val rootLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(Color.parseColor("#F5F5F5")) // Light grey background
            setPadding(16, 16, 16, 16) // Padding around the entire layout
        }

        // 1. RecyclerView for displaying snippets
        val recyclerView = RecyclerView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT // No longer weighted, takes full height
            ).apply {
                // No bottom margin needed if it's the only main component
            }
            setBackgroundColor(Color.WHITE) // White background for the snippet area
            setPadding(8, 8, 8, 8) // Padding inside the RecyclerView
            clipToPadding = false
            clipChildren = false
        }
        rootLayout.addView(recyclerView)

        // Set the root layout as the content view
        setContentView(rootLayout)

        // Pre-populate the snippets list with example data
        snippets.add(SnippetItem(
            originalSnippet = "fun main() {\n    println(\"Hello, Kotlin!\")\n}",
            replacementSnippet = "fun greet(name: String) {\n    println(\"Hello, \$name!\")\n}"
        ))
        snippets.add(SnippetItem(
            originalSnippet = "val num = 10\nif (num > 5) {\n    println(\"Greater\")\n}",
            replacementSnippet = "val value = 7\nwhen (value) {\n    in 1..5 -> println(\"Small\")\n    in 6..10 -> println(\"Medium\")\n    else -> println(\"Large\")\n}"
        ))
        snippets.add(SnippetItem(
            originalSnippet = "class MyClass\nval obj = MyClass()",
            replacementSnippet = "data class User(val name: String, val age: Int)"
        ))
        snippets.add(SnippetItem(
            originalSnippet = "listOf(1, 2, 3).forEach { print(it) }",
            replacementSnippet = "mapOf(\"a\" to 1, \"b\" to 2).forEach { (key, value) -> println(\"\$key: \$value\") }"
        ))
        snippets.add(SnippetItem(
            originalSnippet = "// This is a single-line comment",
            replacementSnippet = "/*\n * This is a\n * multi-line comment\n */"
        ))
        // Add more SnippetItem examples here (up to 40 or more if you wish)
        // For example, a snippet without a replacement:
        snippets.add(SnippetItem(
            originalSnippet = "// A snippet with no replacement"
        ))


        // Initialize RecyclerView with the new SnippetItem list
        snippetAdapter = SnippetAdapter(snippets)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = snippetAdapter

        // Removed: EditText for pasting snippets
        // Removed: Button to add snippet
        // Removed: Add button click listener logic
    }
}