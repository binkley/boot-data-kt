package hm.binkley.spike.bootdatakt.domain

data class Table(
        val name: String,
        val room: Room,
        val stations: List<Station>
)
