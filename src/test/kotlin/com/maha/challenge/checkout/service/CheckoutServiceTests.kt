package com.maha.challenge.checkout.service

import com.maha.challenge.checkout.domain.repositories.DiscountRepository
import com.maha.challenge.checkout.domain.repositories.ItemRepository
import com.maha.challenge.checkout.service.dto.CartPriceDto
import com.maha.challenge.checkout.tool.BaseMock
import com.maha.challenge.checkout.domain.model.Discount
import com.maha.challenge.checkout.domain.model.Item
import org.assertj.core.api.Assertions.assertThat
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.boot.test.mock.mockito.MockBean
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

class CheckoutServiceTests: BaseMock(){

    @MockBean
    private lateinit var itemRepository: ItemRepository
    @MockBean
    private lateinit var discountRepository: DiscountRepository

    private lateinit var checkoutService: CheckoutService

    private val item1: Item = Item(1L,"001","Rolex",100f)
    private val item2: Item = Item(2L,"002","Michael Kors",80f)
    private val item3: Item = Item(3L,"003","Swatch",50f)
    private val item4: Item = Item(4L,"004","Casio",30f)

    private val discount1: Discount = Discount(1L,item1,3,200f)
    private val discount2: Discount = Discount(1L,item2,2,120f)

    @BeforeMethod
    fun setUp() {
        Mockito.reset(itemRepository,discountRepository)
        checkoutService = CheckoutService(itemRepository,discountRepository)
    }


    @Test
    fun `given 3 items with no discount when checkout is performed then price is calculated`() {
        val itemCodes = listOf(item4.code,item3.code,item4.code)
        `when`(itemRepository.findAllByCodeIn(itemCodes.toSet())).thenReturn(listOf(item3,item4))
        `when`(discountRepository.findAllByItemIn(listOf(item3,item4))).thenReturn(emptyList())
        assertThat(checkoutService.calculateCartPrice(itemCodes)).isEqualTo(CartPriceDto(item4.price*2f+item3.price))
    }

    @Test
    fun `given 3 items and one with discount when checkout is performed then price is calculated with discount`() {
        val itemCodes = listOf(item3.code,item4.code,item2.code,item2.code)
        `when`(itemRepository.findAllByCodeIn(itemCodes.toSet())).thenReturn(listOf(item2,item3,item4))
        `when`(discountRepository.findAllByItemIn(listOf(item2,item3,item4))).thenReturn(listOf(discount2))
        assertThat(checkoutService.calculateCartPrice(itemCodes)).isEqualTo(CartPriceDto(item3.price+item4.price+120f))
    }

    @Test
    fun `given 1 item with discount when checkout is performed then price is calculated without discount`() {
        val itemCodes = listOf(item1.code)
        `when`(itemRepository.findAllByCodeIn(itemCodes.toSet())).thenReturn(listOf(item1))
        `when`(discountRepository.findAllByItemIn(listOf(item1))).thenReturn(listOf(discount1))
        assertThat(checkoutService.calculateCartPrice(itemCodes)).isEqualTo(CartPriceDto(item1.price))
    }

    @Test
    fun `given 3 item with discount when checkout is performed then price is calculated with discount`() {
        val itemCodes = listOf(item1.code,item1.code,item1.code)
        `when`(itemRepository.findAllByCodeIn(itemCodes.toSet())).thenReturn(listOf(item1))
        `when`(discountRepository.findAllByItemIn(listOf(item1))).thenReturn(listOf(discount1))
        assertThat(checkoutService.calculateCartPrice(itemCodes)).isEqualTo(CartPriceDto(discount1.price))
    }

    @Test
    fun `given 4 item with discount when checkout is performed then price is calculated with discount`() {
        val itemCodes = listOf(item1.code,item1.code,item1.code,item1.code)
        `when`(itemRepository.findAllByCodeIn(itemCodes.toSet())).thenReturn(listOf(item1))
        `when`(discountRepository.findAllByItemIn(listOf(item1))).thenReturn(listOf(discount1))
        assertThat(checkoutService.calculateCartPrice(itemCodes)).isEqualTo(CartPriceDto(discount1.price+item1.price))
    }

    @Test
    fun `given 6 item with discount when checkout is performed then price is calculated with discount`() {
        val itemCodes = listOf(item1.code,item1.code,item1.code,item1.code,item1.code,item1.code)
        `when`(itemRepository.findAllByCodeIn(itemCodes.toSet())).thenReturn(listOf(item1))
        `when`(discountRepository.findAllByItemIn(listOf(item1))).thenReturn(listOf(discount1))
        assertThat(checkoutService.calculateCartPrice(itemCodes)).isEqualTo(CartPriceDto(discount1.price*2))
    }

}