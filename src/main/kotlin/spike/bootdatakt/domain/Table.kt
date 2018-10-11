package hm.binkley.spike.bootdatakt.domain

import hm.binkley.spike.bootdatakt.store.RoomRecord
import hm.binkley.spike.bootdatakt.store.TableRecord

data class Table(
    val name: String,
    val room: Room,
    val stations: Set<Station>
) {
    fun toStore(room: RoomRecord): TableRecord {
        val table = TableRecord(name)
        table.room = room
        stations.forEach { table.stations.add(it.toStore(table)) }
        return table
    }
}
