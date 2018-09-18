package hm.binkley.spike.bootdatakt.domain

import hm.binkley.spike.bootdatakt.store.StationRecord
import hm.binkley.spike.bootdatakt.store.TableRecord

data class Station(
        val name: String,
        val table: Table) {
    fun toStore(table: TableRecord) = StationRecord(name, table)
}
