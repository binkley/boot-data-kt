package hm.binkley.spike.bootdatakt.store

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(
        path = "people",
        itemResourceRel = "person",
        collectionResourceRel = "people")
interface PersonRepository : JpaRepository<PersonRecord, Long>
