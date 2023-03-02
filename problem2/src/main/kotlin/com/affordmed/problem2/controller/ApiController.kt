package com.affordmed.problem2.controller

import com.affordmed.problem2.model.Response
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


/**
 * @author Udhaya
 * Created on 02-03-2023
 */

private val dataset = listOf(
    "Java",
    "Python",
    "Cpp",
    "JavaScript",
    "Swift",
    "Kotlin",
    "Ruby",
    "PHP",
    "Go",
    "Scala",
    "R",
    "SQL",
    "Perl",
    "Lua",
    "Objective-C",
    "TypeScript",
    "Haskell",
    "Dart",
    "Rust",
    "Visual Basic",
    "Assembly",
    "COBOL",
    "Fortran",
    "Lisp"
).map { it.lowercase() }
private const val FOUND = "found"
private const val NOT_FOUND = "not_found"

@RestController
class ApiController {

    @GetMapping("prefixes")
    fun prefixes(
        @RequestParam("keywords") keywordString: String
    ): List<Response> {
        val keywords = keywordString.split(",")
        val result = keywords.map { it ->
            val keyword = it.lowercase()
            var prefix = ""
            for (c in keyword) {
                prefix += c
                if (dataset.count { it.startsWith(prefix) } == 1)
                    break
            }
            if (keyword in dataset)
                Response(keyword, FOUND, prefix)
            else
                Response(keyword, NOT_FOUND, "not_applicable")
        }
        return result
    }
}