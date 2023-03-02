package com.affordmed.demo.util

import java.net.MalformedURLException
import java.net.URISyntaxException
import java.net.URL


/**
 * @author Udhaya
 * Created on 02-03-2023
 */

fun String.toURL() = try {
    URL(this).toURI()
} catch (e: MalformedURLException) {
    null
} catch (_: URISyntaxException) {
    null
}