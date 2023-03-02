package com.affordmed.demo.network

import com.affordmed.demo.model.Response
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*

interface HttpInterface {
    suspend fun getData(url: String): Response?
}

class HttpInterfaceImpl(
    private val client: HttpClient,
) : HttpInterface {
    override suspend fun getData(url: String) = try {
        val response = client.get {
            url(url)
        }.body<String>()
//        val objectMapper = ObjectMapper()
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//        objectMapper.readValue(response, Response::class.java)
        Gson().fromJson(response, Response::class.java)
    } catch (e: Exception) {
        println(url)
        e.printStackTrace()
        null
    }
}