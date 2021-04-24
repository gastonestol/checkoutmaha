package com.maha.challenge.checkout.domain.repositories

import com.maha.challenge.checkout.domain.model.Discount
import com.maha.challenge.checkout.domain.model.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DiscountRepository: JpaRepository<Discount, Long> {

    fun findAllByItemIn(items: List<Item>): List<Discount>

}