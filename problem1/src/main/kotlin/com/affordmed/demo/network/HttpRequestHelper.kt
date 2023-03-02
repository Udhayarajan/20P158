package com.affordmed.demo.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import java.util.*

interface HttpInterface {
    suspend fun getData(url: String): String?
}

class HttpInterfaceImpl(
    private val client: HttpClient,
) : HttpInterface {
    override suspend fun getData(url: String): String? {
        return try {
            client.get {
                url(url)
            }.run {
                if (status == HttpStatusCode.OK)
                    body()
                else null
            }
        } catch (e: ClientRequestException) {
            null
        } catch (e: Exception) {
            null
        }
    }
}