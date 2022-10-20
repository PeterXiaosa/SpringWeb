package com.example.peter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JavaWebApplication

fun main(args: Array<String>) {
	runApplication<JavaWebApplication>(*args)
}
