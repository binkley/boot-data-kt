package hm.binkley.spike.bootylicious.rest

import hm.binkley.spike.bootylicious.store.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController("/api/people")
class PersonController(
        @Autowired val repository: PersonRepository
) {
    @PostMapping("/register")
    @ResponseStatus(CREATED)
    fun register(@Valid @RequestBody request: PersonRegisterRequest) {
        request.toDomain(repository).register()
    }
}
