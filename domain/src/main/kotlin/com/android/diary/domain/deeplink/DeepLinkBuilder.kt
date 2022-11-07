package com.android.diary.domain.deeplink

data class DeepLinkBuilder(
    private val scheme: Any = "",
    private val host: Any = "",
    private val paths: List<Any> = emptyList(),
    private val queries: Map<Any, Any?> = emptyMap()
) {
    fun setHost(host: Any) = copy(host = host)
    fun addPath(path: Any) = copy(paths = ArrayList(paths).apply { add(path) })
    fun putQuery(key: Any, value: Any?) = copy(queries = HashMap(queries).apply { put(key, value) })
    fun build() = "$scheme://$host${buildPaths()}${buildQueries()}"

    private fun buildPaths() = paths.ifEmpty {
        null
    }?.joinToString(separator = "/", prefix = "/").orEmpty()

    private fun buildQueries() = queries.ifEmpty {
        null
    }?.map {
        "${it.key}=${it.value}"
    }?.joinToString(separator = "&", prefix = "?").orEmpty()
}
