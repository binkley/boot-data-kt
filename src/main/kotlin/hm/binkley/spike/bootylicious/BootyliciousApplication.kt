package hm.binkley.spike.bootylicious

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BootyliciousApplication

fun main(args: Array<String>) {
    runApplication<BootyliciousApplication>(*args)
}
