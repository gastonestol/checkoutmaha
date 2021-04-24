package com.maha.challenge.checkout.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.maha.challenge.checkout.service.CheckoutService
import com.maha.challenge.checkout.service.dto.CartPriceDto
import com.maha.challenge.checkout.tool.BaseMvc
import org.mockito.Mockito.*
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.mockito.Mockito.`when`
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

@ContextConfiguration(classes = [CheckoutController::class])
class CheckoutControllerTests: BaseMvc() {

    @MockBean
    lateinit var checkoutService: CheckoutService

    @BeforeMethod
    fun prepareMockMvc() {
        reset(checkoutService)
    }

    @Test
    fun `given a list of item codes when checkout is invoked then return price calculation`(){
        val items = listOf("001","002","003")
        val priceDto = CartPriceDto(120f)
        `when`(checkoutService.calculateCartPrice(items)).thenReturn(CartPriceDto(120f))

        val url = "/checkout"
        mockMvc.perform(
                post(url).content(jacksonObjectMapper().writeValueAsString(items))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jacksonObjectMapper().writeValueAsString(priceDto)))

    }

}