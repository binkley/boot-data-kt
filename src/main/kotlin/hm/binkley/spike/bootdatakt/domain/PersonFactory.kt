package hm.binkley.spike.bootdatakt.domain

import hm.binkley.spike.bootdatakt.store.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PersonFactory(
        @Autowired private val repository: PersonRepository
) {
    fun person(name: String) = Person(name,
            repository)
}
