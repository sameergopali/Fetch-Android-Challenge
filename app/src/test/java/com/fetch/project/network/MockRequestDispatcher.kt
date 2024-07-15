package com.fetch.project.network

import com.google.common.io.Resources
import mockwebserver3.Dispatcher
import mockwebserver3.MockResponse
import mockwebserver3.RecordedRequest
import java.io.File

class MockRequestDispatcher: Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/test/hiring.json" ->
            {
                MockResponse()
                    .setResponseCode(200)
                    .setBody(getJson("hiring.json"))
            }
            else -> throw IllegalArgumentException("Unknown path ${request.path}")
        }
    }

    private  fun getJson(path: String): String {
        val uri = Resources.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }

}