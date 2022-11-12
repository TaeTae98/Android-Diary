package com.diary.android.timber

import timber.log.Timber

object TimberTree : Timber.DebugTree() {
    override fun createStackElementTag(
        element: StackTraceElement
    ) = "${element.className}:${element.lineNumber}#${element.methodName}"
}
