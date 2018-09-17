package hm.binkley.spike.bootdatakt.store

import org.hibernate.validator.constraints.Length
import java.util.Objects
import javax.persistence.CascadeType.ALL
import javax.persistence.Entity
import javax.persistence.FetchType.EAGER
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "Table")
data class TableRecord(
        @get:NotBlank
        @get:Length(max = 100)
        val name: String = "",
        @Id @GeneratedValue
        val id: Long = Long.MIN_VALUE,
        @OneToMany(mappedBy = "table", cascade = [ALL], fetch = EAGER,
                orphanRemoval = true)
        val stations: MutableSet<StationRecord> = mutableSetOf()) {
    @ManyToOne(fetch = EAGER, cascade = [ALL], optional = false)
    @JoinColumn(name = "room_id")
    private var room: RoomRecord? = null

    fun addTo(room: RoomRecord): TableRecord {
        this.room = room
        return this
    }

    fun removeFrom(): TableRecord {
        this.room = null
        return this
    }

    fun add(station: StationRecord): TableRecord {
        stations.add(station)
        station.addTo(this)
        return this
    }

    fun remove(station: StationRecord): TableRecord {
        stations.remove(station)
        station.removeFrom()
        return this
    }

    override fun equals(other: Any?): Boolean {
        if (null == other || other !is TableRecord) return false

        return id == other.id
                && name == other.name
                && setOf(stations) == setOf(other.stations)
    }

    override fun hashCode(): Int {
        return Objects.hash(id, name, setOf(stations))
    }
}
