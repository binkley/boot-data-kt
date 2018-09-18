package hm.binkley.spike.bootdatakt.rest

import com.fasterxml.jackson.databind.ObjectMapper
import hm.binkley.spike.bootdatakt.store.RoomRecord
import hm.binkley.spike.bootdatakt.store.RoomRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class, MockitoExtension::class)
@WebMvcTest(RoomController::class)
internal class RoomControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var objectMapper: ObjectMapper
    @MockBean
    private lateinit var roomRepository: RoomRepository

    @Test
    fun shouldList() {
        val rooms = listOf(RoomRecord(
                name = "Front"))
        `when`(roomRepository.findAll())
                .thenReturn(rooms)

        mockMvc.perform(get("/api/rooms"))
                .andExpect(status().isOk)
                .andExpect(content().json(asJson(
                        rooms.map { RoomResponse(it) })))
    }

    private fun asJson(obj: Any?) = objectMapper.writeValueAsString(obj)
}
