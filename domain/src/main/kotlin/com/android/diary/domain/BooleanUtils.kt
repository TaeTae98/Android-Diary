package com.android.diary.domain

fun Boolean?.isTrue() = this == true
fun Boolean?.isFalse() = this == false

inline fun Boolean?.onTrue(action: () -> Unit): Boolean? {
    if (isTrue()) {
        action()
    }

    return this
}

inline fun Boolean?.onFalse(action: () -> Unit): Boolean? {
    if (isFalse()) {
        action()
    }

    return this
}