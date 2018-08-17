package hm.binkley.spike.bootylicious.store

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.AUTO
import javax.persistence.Id
import javax.validation.constraints.Max
import javax.validation.constraints.NotBlank

@Entity
class PersonRecord(
        @Id @GeneratedValue(strategy = AUTO)
        val id: Long = 0,
        @get:NotBlank
        @get:Max(100)
        val name: String = ""
)
