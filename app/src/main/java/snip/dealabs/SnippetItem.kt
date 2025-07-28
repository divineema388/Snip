package com.snip.dealabs

/**
 * Data class to represent a single code snippet entry.
 * It holds the main snippet and an optional replacement snippet.
 */
data class SnippetItem(
    val originalSnippet: String,
    val replacementSnippet: String? = null // Nullable if a snippet doesn't have a replacement
)