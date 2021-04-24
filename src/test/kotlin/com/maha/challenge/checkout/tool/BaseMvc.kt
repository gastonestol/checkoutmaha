package com.maha.challenge.checkout.tool

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.testng.annotations.BeforeMethod

@EnableWebMvc
@WebAppConfiguration
@TestExecutionListeners(listeners = [MockitoTestExecutionListener::class])
open class BaseMvc : AbstractTestNGSpringContextTests() {
    @Autowired
    lateinit var wac: WebApplicationContext
    lateinit var mockMvc: MockMvc

    @BeforeMethod
    fun before() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }
}
