package hm.binkley.spike.bootylicious.furniture.store

import org.hibernate.validator.constraints.Length
import javax.persistence.Entity
import javax.persistence.FetchType.EAGER
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "Station")
data class StationRecord(
        @get:NotBlank
        @get:Length(max = 100)
        val name: String = "",
        @Id @GeneratedValue
        val id: Long = Long.MIN_VALUE) {
    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "table_id")
    private var table: TableRecord? = null

    fun addTo(room: TableRecord): StationRecord {
        this.table = room
        return this
    }

    fun removeFrom(): StationRecord {
        this.table = null
        return this
    }

    override fun equals(other: Any?): Boolean {
        if (null == other || other !is RoomRecord) return false;

        return name == other.name
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}
