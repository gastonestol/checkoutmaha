package com.maha.challenge.checkout.domain.model

import javax.persistence.*

@Table(name = "items")
@Entity
data class Item(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val code: String,
    val name: String,
    val price: Float
)