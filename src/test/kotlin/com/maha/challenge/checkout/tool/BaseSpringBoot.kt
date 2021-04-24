package com.maha.challenge.checkout.tool

import com.maha.challenge.checkout.DemoApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests

@SpringBootTest(classes = [DemoApplication::class])
@TestPropertySource(locations = ["classpath:application-test.properties"])
class BaseSpringBoot : AbstractTestNGSpringContextTests()
