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
        @Autowired val furnishingRepository: FurnishingRepository,
        @Autowired val entityManager: TestEntityManager
) {
    @Test
    fun shouldRoundtrip() {
        val saved = repository.saveAndFlush(RoomRecord(
                name = "Bob",
                furniture = mutableListOf(FurnishingRecord(
                        name = "desk"))))
        entityManager.clear()

        assertThat(repository.findById(saved.id))
                .isEqualTo(Optional.of(saved))
    }

    @Test
    fun shouldRemoveOrphans() {
        val saved = repository.saveAndFlush(RoomRecord(
                name = "Bob",
                furniture = mutableListOf(FurnishingRecord(
                        name = "desk"))))
        entityManager.clear()
        val furnishing = saved.furniture[0]
        saved.remove(furnishing)
        val savedAgain = repository.saveAndFlush(saved)

        assertThat(repository.findById(savedAgain.id)
                .map(RoomRecord::furniture)
                .get())
                .isEmpty()
        assertThat(furnishingRepository.findById(furnishing.id)).isEmpty
    }

    @Test
    fun shouldUpdateNicely() {
        val saved = repository.saveAndFlush(RoomRecord(
                name = "Bob",
                furniture = mutableListOf(FurnishingRecord(
                        name = "desk"))))
        entityManager.clear()

        val updated = repository.saveAndFlush(saved.copy(
                name = "Sally",
                furniture = mutableListOf(FurnishingRecord(
                        name = "desk"), FurnishingRecord(
                        name = "chair"))))
        entityManager.clear()

        assertThat(repository.findById(updated.id))
                .isEqualTo(Optional.of(updated))

        println(furnishingRepository.findAll())
    }
}
