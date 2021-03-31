package com.example.banksystem.extensions

import android.text.Editable

fun Editable?.toFloat(): Float {
    if (this == null){
        return 0f
    }

    return this.toString().toFloat()
}