package com.maha.challenge.checkout.tool

import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests

@TestExecutionListeners(listeners = [MockitoTestExecutionListener::class])
open class BaseMock : AbstractTestNGSpringContextTests()
