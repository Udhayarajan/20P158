package com.affordmed.demo.network

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HttpRequest(
    private val url: String,
) {
    private companion object {
        fun createClient(): HttpInterface {
            return HttpInterfaceImpl(HttpClient(Android) {
                followRedirects = true
                install(HttpTimeout) {
//                    requestTimeoutMillis = 80
                }
            })
        }
    }

    /**
     * Fetches plain text for given url
     *
     * @return Text format of entire webpage for given [url]
     */
    suspend fun getResponse() = withContext(Dispatchers.IO) { createClient().getData(url) }

}
