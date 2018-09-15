package hm.binkley.spike.bootdatakt.people.domain

import hm.binkley.spike.bootdatakt.people.store.PersonRecord
import hm.binkley.spike.bootdatakt.people.store.PersonRepository

data class Person(
        val name: String,
        private val repository: PersonRepository
) {
    fun toRecord() = PersonRecord(name)

    fun register() {
        repository.save(toRecord())
    }
}
