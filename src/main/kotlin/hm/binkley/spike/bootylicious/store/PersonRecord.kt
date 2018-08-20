package hm.binkley.spike.bootylicious.store

import org.hibernate.validator.constraints.Length
import org.springframework.data.rest.core.annotation.RestResource
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "Person")
data class PersonRecord(
        @get:NotBlank
        @get:Length(max = 100)
        val name: String = "",
        @Id @GeneratedValue
        val id: Long = Long.MIN_VALUE
)
