package com.example.todolist.common

import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY


fun String.htmlToSpanned() : Spanned {
    return HtmlCompat.fromHtml(this, FROM_HTML_MODE_LEGACY)
}