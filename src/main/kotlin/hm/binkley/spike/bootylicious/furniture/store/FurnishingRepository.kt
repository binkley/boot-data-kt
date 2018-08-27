package hm.binkley.spike.bootylicious.furniture.store

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(
        path = "furniture",
        itemResourceRel = "furnishing",
        collectionResourceRel = "furniture")
interface FurnishingRepository : JpaRepository<FurnishingRecord, Long>
