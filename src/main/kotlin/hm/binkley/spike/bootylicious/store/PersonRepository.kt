package hm.binkley.spike.bootylicious.store

import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository : JpaRepository<PersonRecord, Long>
