package com.affordmed.demo.controller

import com.affordmed.demo.model.Response
import com.affordmed.demo.network.HttpRequest
import com.affordmed.demo.util.toURL
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


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
        val validUrls = urls.map {
            if (it.toURL() != null)
                HttpRequest(it).getResponse()
            else null
        }
        var unique = mutableSetOf<Int>()
        validUrls.forEach {
            it?.let {
                unique.addAll(it.numbers)
            }
        }
        unique = unique.toSortedSet()
        return Response(unique.toList())
    }
}