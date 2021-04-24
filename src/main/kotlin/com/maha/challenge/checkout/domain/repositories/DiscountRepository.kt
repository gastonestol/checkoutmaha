package com.maha.challenge.checkout.domain.repositories

import maha.challenge.checkout.model.Discount
import maha.challenge.checkout.model.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DiscountRepository: JpaRepository<Discount, Long> {

    fun findAllByItemIn(items: List<Item>): List<Discount>

}