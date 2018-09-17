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
        @Autowired val entityManager: TestEntityManager) {
    @Test
    fun shouldRoundtripWithEntityManager() {
        val room = RoomRecord(
                name = "Bob")
                .add(TableRecord(
                        name = "Front"))
        val saved = entityManager.persistFlushFind(room)

        assertThat(saved).isEqualTo(room)
    }

    @Test
    fun shouldRemoveOrphansWithEntityManager() {
        val room = RoomRecord(
                name = "Bob")
                .add(TableRecord(
                        name = "Front"))
        var saved = entityManager.persistFlushFind(room)

        val furnishing = saved.tables.first()
        saved.remove(furnishing)
        saved = entityManager.persistFlushFind(saved)

        assertThat(saved.tables)
                .isEmpty()
        assertThat(tableRepository.findById(furnishing.id)).isEmpty
    }

    @Test
    fun shouldUpdateChildrenWithEntityManager() {
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

    @Test
    fun shouldUpdateGrandchildrenWithEntityManager() {
        val table = TableRecord(
                name = "Front")
        val room = RoomRecord(
                name = "Bob")
                .add(table)
        var saved = entityManager.persistFlushFind(room)

        saved.tables.first().add(StationRecord(
                name = "Science"))

        saved = entityManager.persistFlushFind(saved)

        assertThat(saved.tables.first().stations).hasSize(1)
        assertThat(stationRepository.count()).isEqualTo(1)

        saved.tables.first().add(StationRecord(
                name = "Helm"))

        saved = entityManager.persistFlushFind(saved)

        assertThat(saved.tables.first().stations).hasSize(2)
        assertThat(stationRepository.count()).isEqualTo(2)
    }

    @Test
    fun shouldUpdateGrandchildrenWithRepository() {
        val unsaved = RoomRecord(
                name = "Bob")
                .add(TableRecord(
                        name = "Front"))
        var saved = roomRepository.save(unsaved)

        saved.tables.first().add(StationRecord(
                name = "Science"))

        saved = roomRepository.save(saved)

        assertThat(saved.tables.first().stations).hasSize(1)
        assertThat(stationRepository.count()).isEqualTo(1)

        saved.tables.first().add(StationRecord(
                name = "Helm"))

        saved = roomRepository.save(saved)

        assertThat(saved.tables.first().stations).hasSize(2)
        assertThat(stationRepository.count()).isEqualTo(2)

        assertThat(entityManager.persistFlushFind(saved)).isEqualTo(saved)
    }
}
