package hm.binkley.spike.bootdatakt.rest

import hm.binkley.spike.bootdatakt.store.TableRecord

data class TableResponse(private val record: TableRecord) {
    val name: String = record.name
    val stations: Set<StationResponse> = record.stations.map { StationResponse(it) }.toSet()
}
