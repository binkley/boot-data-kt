package hm.binkley.spike.bootdatakt.furniture.store

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(
        path = "rooms",
        itemResourceRel = "room",
        collectionResourceRel = "rooms")
interface RoomRepository : JpaRepository<RoomRecord, Long>
