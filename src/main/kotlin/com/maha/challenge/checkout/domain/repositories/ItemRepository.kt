package com.maha.challenge.checkout.domain.repositories

import maha.challenge.checkout.model.Discount
import maha.challenge.checkout.model.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository: JpaRepository<Item, Long> {

    fun findAllByCodeIn(codes: Set<String>): List<Item>

}