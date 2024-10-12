package me.writt.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WrittServerApplication

fun main(args: Array<String>) {
    runApplication<WrittServerApplication>(*args)
}
