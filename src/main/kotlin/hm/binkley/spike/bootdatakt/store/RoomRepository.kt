package hm.binkley.spike.bootdatakt.store

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(
        path = "rooms",
        itemResourceRel = "room",
        collectionResourceRel = "rooms")
interface RoomRepository : JpaRepository<RoomRecord, Long> {
    @JvmDefault
    fun saveWhole(room: RoomRecord, tableRepository: TableRepository)
            : RoomRecord {
        val saved = saveAndFlush(room)
        saved.tables.forEach { tableRepository.saveAndFlush(it) }
        return saved
    }
}
