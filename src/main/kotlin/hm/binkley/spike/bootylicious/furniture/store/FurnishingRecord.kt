package hm.binkley.spike.bootylicious.furniture.store

import org.hibernate.validator.constraints.Length
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "Furnishing")
data class FurnishingRecord(
        @get:NotBlank
        @get:Length(max = 100)
        val name: String = "",
        @Id @GeneratedValue
        val id: Long = Long.MIN_VALUE)
