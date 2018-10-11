package hm.binkley.spike.bootdatakt.domain

import hm.binkley.spike.bootdatakt.store.StationRecord
import hm.binkley.spike.bootdatakt.store.TableRecord

data class Station(
    val name: String,
    val table: Table
) {
    fun toStore(table: TableRecord): StationRecord {
        val station = StationRecord(name)
        station.table = table
        return station
    }
}
