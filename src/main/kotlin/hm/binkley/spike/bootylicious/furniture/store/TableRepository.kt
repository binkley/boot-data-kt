package hm.binkley.spike.bootylicious.furniture.store

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(
        path = "tables",
        itemResourceRel = "table",
        collectionResourceRel = "tables")
interface TableRepository : JpaRepository<TableRecord, Long>