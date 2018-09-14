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
        val room = RoomRecord(
                name = "Bob")
                .add(FurnishingRecord(
                        name = "desk"))
        val saved = entityManager.persistFlushFind(room)

        assertThat(saved).isEqualTo(room)
    }

    @Test
    fun shouldRemoveOrphans() {
        val room = RoomRecord(
                name = "Bob")
                .add(FurnishingRecord(
                        name = "desk"))
        var saved = entityManager.persistFlushFind(room)

        val furnishing = saved.furniture[0]
        saved.remove(furnishing)
        saved = entityManager.persistFlushFind(saved)

        assertThat(saved.furniture)
                .isEmpty()
        assertThat(furnishingRepository.findById(furnishing.id)).isEmpty
    }

    @Test
    fun shouldUpdateNicely() {
        val room = RoomRecord(
                name = "Bob")
                .add(FurnishingRecord(
                        name = "desk"))
        var saved = entityManager.persistFlushFind(room)

        saved = entityManager.persistFlushFind(saved
                .add(FurnishingRecord(
                        name = "chair")))

        assertThat(saved.furniture.size).isEqualTo(2)
        assertThat(furnishingRepository.count()).isEqualTo(2)
    }
}
