package com.diary.android.domain.utils

fun Boolean?.isTrue() = this == true
fun Boolean?.isFalse() = this == false
fun Boolean?.isNullOrFalse() = this == null || this == false

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

inline fun Boolean?.onNullOrFalse(action: () -> Unit): Boolean? {
    if (isNullOrFalse()) {
        action()
    }

    return this
}
