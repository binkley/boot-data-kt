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
@Table(name = "Table")
data class TableRecord(
        @get:NotBlank
        @get:Length(max = 100)
        val name: String = "",
        @Id @GeneratedValue
        val id: Long = Long.MIN_VALUE) {
    @ManyToOne(fetch = EAGER)
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
}