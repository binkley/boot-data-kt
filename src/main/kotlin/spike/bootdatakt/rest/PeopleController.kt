package hm.binkley.spike.bootdatakt.rest

import hm.binkley.spike.bootdatakt.domain.PersonFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/people")
class PeopleController(
    @Autowired private val personFactory: PersonFactory
) {
    @PostMapping("register")
    @ResponseStatus(CREATED)
    fun register(@Valid @RequestBody request: PersonRegisterRequest) =
        request.toDomain(personFactory).register()
}
