package com.spt.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Required
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Component


@SpringBootApplication
class Model @Autowired constructor(val send : Prog, val recv : Prog2) {

    @Component
    companion object {
        val host: String = "192.168.1.19"
    }
    @Required
    @Autowired
    fun send(){

        send.send("lalala \n\n\n  oui oui oui yyyy")

    }




}