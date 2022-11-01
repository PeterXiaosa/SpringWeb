package com.example.peter

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@MapperScan("com.example.peter.mapper")
@SpringBootApplication
class JavaWebApplication

fun main(args: Array<String>) {
	runApplication<JavaWebApplication>(*args)
}
