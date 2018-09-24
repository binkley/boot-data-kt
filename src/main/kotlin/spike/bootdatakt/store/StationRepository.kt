package hm.binkley.spike.bootdatakt.store

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(
        path = "stations",
        itemResourceRel = "station",
        collectionResourceRel = "stations")
interface StationRepository : JpaRepository<StationRecord, Long>
