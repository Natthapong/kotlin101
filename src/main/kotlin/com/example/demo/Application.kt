package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.blockhound.BlockHound

@SpringBootApplication
class PostApplication

fun main(args: Array<String>) {
    BlockHound.builder()
        .with {
            it.allowBlockingCallsInside("RandomAccessFile", "readBytes")
        }.install()

    runApplication<PostApplication>(*args)
}


