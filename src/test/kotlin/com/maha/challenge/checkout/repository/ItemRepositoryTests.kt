package com.maha.challenge.checkout.repository

import com.maha.challenge.checkout.DemoApplication
import com.maha.challenge.checkout.domain.model.Discount
import com.maha.challenge.checkout.domain.model.Item
import com.maha.challenge.checkout.domain.repositories.ItemRepository
import com.maha.challenge.checkout.tool.BaseSpringBoot
import org.assertj.core.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.testng.annotations.AfterMethod
import org.testng.annotations.Test

@SpringBootTest(classes = [DemoApplication::class])
@TestPropertySource(locations = ["classpath:application-test.properties"])
class ItemRepositoryTests: BaseSpringBoot() {

    @Autowired
    private lateinit var itemRepository: ItemRepository

    @Test
    fun `given a new item when is saved then is correctly persisted`(){
        var item = Item(null,"001","TEST",100f)
        item = itemRepository.save(item)
        Assertions.assertThat(itemRepository.findById(item.id!!).isPresent).isTrue()
    }

    @Test
    fun `given an items list with one fake id when findAllItemsByCode then return items that exist`(){
        var item1 = Item(null,"001","TEST",100f)
        item1 = itemRepository.save(item1)
        var item2 = Item(null,"001","TEST",100f)
        item2 = itemRepository.save(item2)
        var item3 = Item(null,"001","TEST",100f)
        item3 = itemRepository.save(item3)
        val extistingItems = itemRepository.findAllByCodeIn(setOf(item1.code,item2.code,item3.code,"004"))
        Assertions.assertThat(extistingItems.size).isEqualTo(3)
        Assertions.assertThat(extistingItems.first().id).isEqualTo(item1.id)
    }

    @AfterMethod
    fun clear() {
        itemRepository.deleteAll()
    }
}