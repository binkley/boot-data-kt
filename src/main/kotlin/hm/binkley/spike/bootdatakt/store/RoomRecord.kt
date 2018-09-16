package hm.binkley.spike.bootdatakt.store

import org.hibernate.validator.constraints.Length
import java.util.Objects
import javax.persistence.*
import javax.persistence.CascadeType.ALL
import javax.persistence.FetchType.EAGER
import javax.validation.constraints.NotBlank
import kotlin.collections.ArrayList

@Entity
@Table(name = "Room")
data class RoomRecord(
        @get:NotBlank
        @get:Length(max = 100)
        val name: String = "") {
    @Id
    @GeneratedValue
    val id: Long = Long.MIN_VALUE
    @OneToMany(mappedBy = "room", cascade = [ALL], fetch = EAGER, orphanRemoval = true)
    val tables: MutableList<TableRecord> = mutableListOf()

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
        if (null == other || other !is RoomRecord) return false

        return name == other.name
                && ArrayList(tables) == ArrayList(other.tables)
    }

    override fun hashCode(): Int {
        return Objects.hash(name, ArrayList(tables))
    }
}
