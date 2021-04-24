package com.maha.challenge.checkout.controller

import com.maha.challenge.checkout.service.dto.CartPriceDto
import com.maha.challenge.checkout.service.CheckoutService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CheckoutController(private val checkoutService: CheckoutService) {

    @PostMapping("/checkout",headers=["Accept=application/json","Content-Type=application/json"])
    fun checkout(@RequestBody items: List<String>): ResponseEntity<CartPriceDto> {
        return ResponseEntity.status(HttpStatus.OK).body(checkoutService.calculateCartPrice(items))
    }

}