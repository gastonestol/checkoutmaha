package com.maha.challenge.checkout

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoApplication {
	companion object {
		@JvmStatic fun main(args: Array<String>) {
			runApplication<DemoApplication>(*args)
		}
	}
}