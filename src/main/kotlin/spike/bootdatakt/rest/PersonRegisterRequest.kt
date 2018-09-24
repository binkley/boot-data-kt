package hm.binkley.spike.bootdatakt.rest

import hm.binkley.spike.bootdatakt.domain.PersonFactory
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank

data class PersonRegisterRequest(
        @get:NotBlank
        @get:Length(max = 100)
        val name: String = ""
) {
    fun toDomain(personFactory: PersonFactory) = personFactory.person(name)
}
