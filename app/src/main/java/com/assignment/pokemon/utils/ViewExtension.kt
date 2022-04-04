package com.assignment.pokemon.utils

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

fun Context?.colorStateList(@ColorRes colorRes: Int) =
    this?.let { ContextCompat.getColorStateList(it, colorRes) }
        ?: ColorStateList.valueOf(Color.TRANSPARENT)

fun String?.capital(): String {
    return this?.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    }.toString()
}

fun EditText.addDelayOnTypeWithScope(
    delayMillis: Long = 0,
    scope: CoroutineScope,
    action: (String) -> Unit
) {
    var job: Job? = null

    addTextChangedListener {
        job?.cancel()

        job = scope.launch {
            delay(delayMillis)
            action(it.toString())
        }
    }
}

fun Activity?.hideKeyboard(view: View) {
    val imm = this?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(view.windowToken, 0)
}