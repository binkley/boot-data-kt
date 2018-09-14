package hm.binkley.spike.bootylicious.furniture.store

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit.jupiter.SpringExtension

@DataJpaTest
@ExtendWith(SpringExtension::class)
internal class RoomRepositoryTest(
        @Autowired val repository: RoomRepository,
        @Autowired val furnishingRepository: FurnishingRepository,
        @Autowired val entityManager: TestEntityManager
) {
    @Test
    fun shouldRoundtrip() {
        val saved = repository.save(RoomRecord(
                name = "Bob")
                .add(FurnishingRecord(
                        name = "desk")))

        entityManager.flush()
        entityManager.clear()

        assertThat(repository.getOne(saved.id))
                .isEqualTo(saved)
    }

    @Test
    fun shouldRemoveOrphans() {
        val saved = repository.save(RoomRecord(
                name = "Bob")
                .add(FurnishingRecord(
                        name = "desk")))

        entityManager.flush()
        entityManager.clear()

        val furnishing = saved.furniture[0]
        saved.remove(furnishing)
        val savedAgain = repository.save(saved)

        entityManager.flush()
        entityManager.clear()

        assertThat(repository.findById(savedAgain.id)
                .map(RoomRecord::furniture)
                .get())
                .isEmpty()
        assertThat(furnishingRepository.findById(furnishing.id)).isEmpty
    }

    @Test
    fun shouldUpdateNicely() {
        val saved = repository.save(RoomRecord(
                name = "Bob")
                .add(FurnishingRecord(
                        name = "desk")))
        entityManager.flush()
        entityManager.clear()

        val updated = repository.save(saved.copy(
                name = "Sally"))
                .add(FurnishingRecord(
                        name = "chair"))
        entityManager.flush()
        entityManager.clear()

        assertThat(repository.getOne(updated.id))
                .isEqualTo(updated)

        println(furnishingRepository.findAll())
    }
}
