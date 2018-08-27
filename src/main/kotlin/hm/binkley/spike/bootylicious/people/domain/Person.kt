package hm.binkley.spike.bootylicious.people.domain

import hm.binkley.spike.bootylicious.people.store.PersonRecord
import hm.binkley.spike.bootylicious.people.store.PersonRepository

data class Person(
        val name: String,
        private val repository: PersonRepository
) {
    fun toRecord() = PersonRecord(name)

    fun register() {
        repository.save(toRecord())
    }
}
