package hm.binkley.spike.bootdatakt.rest

import hm.binkley.spike.bootdatakt.store.RoomRecord

data class RoomResponse(private val record: RoomRecord) {
    val name: String = record.name
    val tables: List<TableResponse> = record.tables
            .map { TableResponse(it) }
}
