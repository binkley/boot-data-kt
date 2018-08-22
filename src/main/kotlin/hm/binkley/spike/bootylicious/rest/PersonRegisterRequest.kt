package hm.binkley.spike.bootylicious.rest

import hm.binkley.spike.bootylicious.service.Person
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank

data class PersonRegisterRequest(
        @get:NotBlank
        @get:Length(max = 100)
        val name: String = "") {
    fun toDomain() = Person(name)
}
