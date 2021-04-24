package com.maha.challenge.checkout.domain.model

import javax.persistence.*

@Table(name = "discounts")
@Entity
class Discount (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null,
        @ManyToOne(fetch = FetchType.LAZY)
        val item: Item,
        val quantity: Long,
        val price: Float
)