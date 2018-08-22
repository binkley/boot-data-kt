package hm.binkley.spike.bootylicious.service

import hm.binkley.spike.bootylicious.store.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PersonService(@Autowired val repository: PersonRepository) {
    fun register(person: Person): Unit {
        repository.save(person.toRecord())
    }
}
