package hm.binkley.spike.bootylicious.store

import org.junit.Test
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
internal class PersonRepositoryTest(
        val repository: PersonRepository
) {
    @Test
    fun shouldRoundtrip() {
    }
}
