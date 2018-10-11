package hm.binkley.spike.bootdatakt.domain

import hm.binkley.spike.bootdatakt.store.PersonRecord
import hm.binkley.spike.bootdatakt.store.PersonRepository

data class Person(
    val name: String,
    private val repository: PersonRepository
) {
    fun toRecord() = PersonRecord(name)

    fun register() {
        repository.save(toRecord())
    }
}
