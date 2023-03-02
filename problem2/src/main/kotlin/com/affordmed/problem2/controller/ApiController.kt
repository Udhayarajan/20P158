package com.affordmed.problem2.controller

import com.affordmed.problem2.model.Response
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


/**
 * @author Udhaya
 * Created on 02-03-2023
 */

private val dataset = arrayOf(
    "Java",
    "Python",
    "C++",
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
    "F#",
    "Dart",
    "Rust",
    "Visual Basic",
    "Assembly",
    "COBOL",
    "Fortran",
    "Lisp"
)
private const val FOUND = "found"
private const val NOT_FOUND = "not_found"

@RestController
class ApiController {

    @GetMapping("prefixes")
    fun prefixes(
        @RequestParam("keywords") keywordString: String
    ) {
        val keywords = keywordString.split(".")
        val result = keywords.map { keyword ->
            if (keyword in dataset)
                Response(keyword, FOUND, "")
            else
                Response(keyword, NOT_FOUND, "")
        }
    }
}