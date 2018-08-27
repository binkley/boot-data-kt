package hm.binkley.spike.bootylicious.people.domain

import hm.binkley.spike.bootylicious.people.store.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PersonFactory(
        @Autowired private val repository: PersonRepository
) {
    fun person(name: String) = Person(name, repository)
}
