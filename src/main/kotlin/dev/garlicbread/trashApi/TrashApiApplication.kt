package dev.garlicbread.trashApi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TrashApiApplication

fun main(args: Array<String>) {
    runApplication<TrashApiApplication>(*args)
}
