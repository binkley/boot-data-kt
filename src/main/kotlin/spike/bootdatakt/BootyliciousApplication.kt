package hm.binkley.spike.bootdatakt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.util.logging.Logger.getLogger


@SpringBootApplication
class BootyliciousApplication {
    @Component
    class Ready {
        @EventListener
        fun onApplicationEvent(event: ApplicationReadyEvent) {
            log.info(event.toString())
        }
    }

    companion object {
        private val log = getLogger(BootyliciousApplication::class.toString())
    }
}

fun main(args: Array<String>) {
    runApplication<BootyliciousApplication>(*args)
}
