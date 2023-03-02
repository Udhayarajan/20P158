package com.affordmed.problem2.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


/**
 * @author Udhaya
 * Created on 02-03-2023
 */

private val dataset = arrayOf("java", "kotlin", "typescript", "javascript")
@RestController
class ApiController {

    @GetMapping("prefixes")
    fun prefixes(
        @RequestParam("keywords") keywordString: String
    ){
        val keywords = keywordString.split(".")

    }
}