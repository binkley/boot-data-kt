package hm.binkley.spike.bootdatakt.rest

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class, MockitoExtension::class)
@WebMvcTest(RoomController::class)
internal class RoomControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc
    @MockBean
    private lateinit var roomRepository: RoomRepository

    @Test
    fun shouldList() {
        `when`(roomRepository.findAll())
                .thenReturn(listOf(RoomRecord(
                        name = "Front")))

        mockMvc.perform(get("/api/rooms"))
                .andExpect(status().isOk)
    }
}
