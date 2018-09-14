package hm.binkley.spike.bootylicious.furniture.store

import org.hibernate.validator.constraints.Length
import javax.persistence.CascadeType.ALL
import javax.persistence.Entity
import javax.persistence.FetchType.EAGER
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "Room")
data class RoomRecord(
        @get:NotBlank
        @get:Length(max = 100)
        val name: String = "",
        @OneToMany(mappedBy = "room", cascade = [ALL], fetch = EAGER,
                orphanRemoval = true)
        val tables: MutableList<TableRecord> = mutableListOf(),
        @Id @GeneratedValue
        val id: Long = Long.MIN_VALUE) {

    fun add(table: TableRecord): RoomRecord {
        tables.add(table)
        table.addTo(this)
        return this
    }

    fun remove(table: TableRecord): RoomRecord {
        tables.remove(table)
        table.removeFrom()
        return this
    }

    override fun equals(other: Any?): Boolean {
        if (null == other || other !is RoomRecord) return false;

        return name == other.name
                && ArrayList(tables) == ArrayList(other.tables)
    }
}
