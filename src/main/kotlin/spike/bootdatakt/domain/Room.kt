package hm.binkley.spike.bootdatakt.domain

import hm.binkley.spike.bootdatakt.store.RoomRecord

data class Room(
    val name: String,
    val tables: Set<Table>
) {
    fun toStore(): RoomRecord {
        val room = RoomRecord(name)
        tables.forEach { room.tables.add(it.toStore(room)) }
        return room
    }
}
