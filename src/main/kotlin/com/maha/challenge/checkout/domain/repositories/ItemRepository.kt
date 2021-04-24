package com.maha.challenge.checkout.domain.repositories

import com.maha.challenge.checkout.domain.model.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository: JpaRepository<Item, Long> {

    fun findAllByCodeIn(codes: Set<String>): List<Item>

}