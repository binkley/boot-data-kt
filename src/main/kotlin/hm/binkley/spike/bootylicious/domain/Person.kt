package hm.binkley.spike.bootylicious.domain

import hm.binkley.spike.bootylicious.store.PersonRecord
import hm.binkley.spike.bootylicious.store.PersonRepository

data class Person(
        val name: String,
        private val repository: PersonRepository
) {
    fun toRecord() = PersonRecord(name)

    fun register(): Unit {
        repository.save(toRecord())
    }
}
