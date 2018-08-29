package hm.binkley.spike.bootylicious.furniture.store

import org.hibernate.validator.constraints.Length
import javax.persistence.CascadeType.ALL
import javax.persistence.Entity
import javax.persistence.FetchType.EAGER
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "Room")
data class RoomRecord(
        @get:NotBlank
        @get:Length(max = 100)
        val name: String = "",
        @OneToMany(cascade = [ALL], fetch = EAGER, orphanRemoval = true)
        @JoinColumn(name = "room_id")
        val furniture: MutableList<FurnishingRecord> = mutableListOf(),
        @Id @GeneratedValue
        val id: Long = Long.MIN_VALUE) {

    fun add(furnishing: FurnishingRecord): RoomRecord {
        furniture.add(furnishing)
        return this
    }

    fun remove(furnishing: FurnishingRecord): RoomRecord {
        furniture.remove(furnishing)
        return this
    }

    override fun equals(other: Any?): Boolean {
        if (null == other || other !is RoomRecord) return false;

        return name == other.name
                && ArrayList(furniture) == ArrayList(other.furniture)
    }
}
