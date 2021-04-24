package maha.challenge.checkout.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
data class Item(
    @Id
    var id: Long? = null,
    var code: String,
    var name: String,
    var price: Float
) {
}