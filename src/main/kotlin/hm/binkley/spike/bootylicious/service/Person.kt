package hm.binkley.spike.bootylicious.service

import hm.binkley.spike.bootylicious.store.PersonRecord

data class Person(val name: String) {
    fun toRecord() = PersonRecord(name)
}
