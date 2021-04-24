package com.maha.challenge.checkout.repository

import com.maha.challenge.checkout.DemoApplication
import com.maha.challenge.checkout.domain.repositories.DiscountRepository
import com.maha.challenge.checkout.domain.repositories.ItemRepository
import com.maha.challenge.checkout.tool.BaseSpringBoot
import com.maha.challenge.checkout.domain.model.Discount
import com.maha.challenge.checkout.domain.model.Item
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.testng.annotations.AfterMethod
import org.testng.annotations.Test

@SpringBootTest(classes = [DemoApplication::class])
@TestPropertySource(locations = ["classpath:application-test.properties"])
class DiscountRepositoryTests: BaseSpringBoot() {

    @Autowired
    private lateinit var discountRepository: DiscountRepository
    @Autowired
    private lateinit var itemRepository: ItemRepository

    @Test
    fun `given a new discount when is saved then is correctly persisted`(){
        var item = Item(null,"001","TEST",100f)
        item = itemRepository.save(item)
        var discount = Discount(null,item,2,100f)
        discount = discountRepository.save(discount)
        assertThat(discountRepository.findById(discount.id!!).isPresent).isTrue()
    }

    @Test
    fun `given an items list when findAllDiscountsByItemIn then return discount that have those items`(){
        var item1 = Item(null,"001","TEST",100f)
        item1 = itemRepository.save(item1)
        var item2 = Item(null,"001","TEST",100f)
        item2 = itemRepository.save(item2)
        var item3 = Item(null,"001","TEST",100f)
        item3 = itemRepository.save(item3)
        var discount = Discount(null,item1,2,100f)
        discount = discountRepository.save(discount)
        var itemsWithDiscount = discountRepository.findAllByItemIn(listOf(item1,item2,item3))
        assertThat(itemsWithDiscount.size).isEqualTo(1)
        assertThat(itemsWithDiscount.first().id).isEqualTo(discount.id)
    }

    @AfterMethod
    fun clear() {
        discountRepository.deleteAll()
    }
}