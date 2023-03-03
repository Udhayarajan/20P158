package com.affordmed.demo.controller

import com.affordmed.demo.model.Response
import com.affordmed.demo.network.HttpRequest
import com.affordmed.demo.util.toURL
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*


/**
 * @author Udhaya
 * Created on 02-03-2023
 */

@RestController
class Problem1Controller {

    @GetMapping("/numbers")
    suspend fun numbers(
        @RequestParam("url") urls: List<String>
    ): Response {
        val response = getResponses(urls)
        val unique = TreeSet<Int>()
        response.forEach {
            it?.let { it1 -> unique.addAll(it1) }
        }
        return Response(unique)
    }

    suspend fun getResponses(urls: List<String?>): List<SortedSet<Int>?> {
        val numbers = mutableListOf<Deferred<SortedSet<Int>?>>()
        coroutineScope {
            for (url in urls) {
                val response = async { url?.toURL()?.toString()?.let { HttpRequest(it).getResponse()?.numbers } }
                numbers.add(response)
            }
        }
        return numbers.awaitAll()
    }
}