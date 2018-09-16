package hm.binkley.spike.bootdatakt.store

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
        @Autowired val roomRepository: RoomRepository,
        @Autowired val tableRepository: TableRepository,
        @Autowired val stationRepository: StationRepository,
        @Autowired val entityManager: TestEntityManager
) {
    @Test
    fun shouldRoundtrip() {
        val room = RoomRecord(
                name = "Bob")
                .add(TableRecord(
                        name = "Front"))
        val saved = entityManager.persistFlushFind(room)

        assertThat(saved).isEqualTo(room)
    }

    @Test
    fun shouldRemoveOrphans() {
        val room = RoomRecord(
                name = "Bob")
                .add(TableRecord(
                        name = "Front"))
        var saved = entityManager.persistFlushFind(room)

        val furnishing = saved.tables[0]
        saved.remove(furnishing)
        saved = entityManager.persistFlushFind(saved)

        assertThat(saved.tables)
                .isEmpty()
        assertThat(tableRepository.findById(furnishing.id)).isEmpty
    }

    @Test
    fun shouldUpdateChildren() {
        val room = RoomRecord(
                name = "Bob")
                .add(TableRecord(
                        name = "Front"))
        var saved = entityManager.persistFlushFind(room)

        saved = entityManager.persistFlushFind(saved
                .add(TableRecord(
                        name = "Back")))

        assertThat(saved.tables.size).isEqualTo(2)
        assertThat(tableRepository.count()).isEqualTo(2)
    }
}
