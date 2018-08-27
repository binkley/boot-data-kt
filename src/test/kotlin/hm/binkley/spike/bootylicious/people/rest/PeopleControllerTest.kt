package hm.binkley.spike.bootylicious.people.rest

import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockito_kotlin.whenever
import hm.binkley.spike.bootylicious.people.domain.Person
import hm.binkley.spike.bootylicious.people.domain.PersonFactory
import hm.binkley.spike.bootylicious.people.store.PersonRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest
internal class PeopleControllerTest(
        @Autowired val mvc: MockMvc,
        @Autowired val objectMapper: ObjectMapper
) {
    @MockBean
    private lateinit var personFactory: PersonFactory
    @MockBean
    private lateinit var repository: PersonRepository

    @Test
    fun shouldWork() {
        val name = "Bob"
        whenever(personFactory.person(name))
                .thenReturn(Person(name, repository))

        mvc.perform(post("/api/people/register")
                .contentType(APPLICATION_JSON_UTF8)
                .content(asJson(PersonRegisterRequest(name))))
                .andExpect(status().isCreated)
    }

    private fun asJson(content: Any) = objectMapper.writeValueAsString(
            content)
}
