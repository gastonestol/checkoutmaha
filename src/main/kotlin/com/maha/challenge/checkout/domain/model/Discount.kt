package maha.challenge.checkout.model

import javax.persistence.*

@Entity
class Discount (
    @Id
    var id: Long? = null,
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    var item: Item,
    var quantity: Long,
    var price: Float
)