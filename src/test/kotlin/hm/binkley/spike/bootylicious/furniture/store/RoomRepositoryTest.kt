package hm.binkley.spike.bootylicious.furniture.store

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.Optional

@DataJpaTest
@ExtendWith(SpringExtension::class)
internal class RoomRepositoryTest(
        @Autowired val repository: RoomRepository,
        @Autowired val entityManager: TestEntityManager
) {
    @Test
    fun shouldRoundtrip() {
        val saved = repository.saveAndFlush(RoomRecord(
                name = "Bob",
                furniture = listOf(FurnishingRecord(name = "desk"))))
        entityManager.clear()

        assertThat(repository.findById(saved.id))
                .isEqualTo(Optional.of(saved))
    }
}
