package hm.binkley.spike.bootylicious.store

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "people")
interface PersonRepository : JpaRepository<PersonRecord, Long>
