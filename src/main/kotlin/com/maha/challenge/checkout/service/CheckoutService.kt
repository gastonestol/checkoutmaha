package com.maha.challenge.checkout.service

import com.maha.challenge.checkout.domain.repositories.DiscountRepository
import com.maha.challenge.checkout.domain.repositories.ItemRepository
import com.maha.challenge.checkout.service.dto.CartPriceDto
import com.maha.challenge.checkout.domain.model.Discount
import com.maha.challenge.checkout.domain.model.Item
import org.springframework.stereotype.Service

@Service
class CheckoutService(
        private val itemRepository: ItemRepository,
        private val discountRepository: DiscountRepository
) {

    fun calculateCartPrice(cart: List<String>): CartPriceDto {
        val countedItems = cart.groupingBy { it }.eachCount()
        val itemsList = itemRepository.findAllByCodeIn(countedItems.keys)
        val discounts = discountRepository.findAllByItemIn(itemsList)
        return CartPriceDto(calculateDiscounts(countedItems,itemsList,discounts))
    }

    private fun calculateDiscounts(countedItems: Map<String,Int>,
                                   itemsList: List<Item>,
                                   discounts: List<Discount>): Float{
        return itemsList.map { item ->
            val count = countedItems.getValue(item.code)
            val calculatedPrice = discounts.find { it.item == item && it.quantity <= count }
            ?.let { discount ->
                val discountTimes = count / discount.quantity
                val reminderTimes = count % discount.quantity
                discountTimes * discount.price + reminderTimes * item.price
            } ?: run {
                count * item.price
            }
            calculatedPrice
        }.reduce { sum, calculatedPrice -> sum + calculatedPrice }
    }
}