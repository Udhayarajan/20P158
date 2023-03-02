package com.affordmed.demo.controller

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
    fun numbers(
        @RequestParam("url") urls: List<String>
    ) {

    }
}