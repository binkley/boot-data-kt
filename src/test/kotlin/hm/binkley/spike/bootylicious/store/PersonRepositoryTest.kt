package hm.binkley.spike.bootylicious.store

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@DataJpaTest
@ExtendWith(SpringExtension::class)
internal class PersonRepositoryTest(
        @Autowired val repository: PersonRepository
) {
    @Test
    fun shouldRoundtrip() {
        val saved = repository.saveAndFlush(PersonRecord(name = "Bob"))

        assertThat(repository.findById(saved.id)).isEqualTo(saved)
    }
}
