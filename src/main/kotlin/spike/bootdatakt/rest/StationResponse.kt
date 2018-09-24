package hm.binkley.spike.bootdatakt.rest

import hm.binkley.spike.bootdatakt.store.StationRecord

data class StationResponse(private val record: StationRecord) {
    val name: String = record.name
}
