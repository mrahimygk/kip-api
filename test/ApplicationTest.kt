package ir.mrahimygk

import application.mainModule
import io.ktor.http.*
import kotlin.test.*
import io.ktor.server.testing.*
import routing.routeutil.helloLocationRoute

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ mainModule() }) {
            handleRequest(HttpMethod.Get, helloLocationRoute).apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("HELLO WORLD", response.content)
            }
        }
    }
}
