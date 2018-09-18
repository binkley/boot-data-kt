package hm.binkley.spike.bootdatakt.rest

import hm.binkley.spike.bootdatakt.store.RoomRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class RoomController(
        @Autowired private val roomRepository: RoomRepository) {
    @GetMapping("rooms")
    fun list() = roomRepository.findAll()
            .map { RoomResponse(it) }
}
