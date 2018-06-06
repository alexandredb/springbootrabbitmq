package com.spt.demo

import org.springframework.boot.SpringApplication

fun main(args: Array<String>) {

    /*
    var host : String = "192.168.1.19"




    var s: Send = Send()
    var r : Recv = Recv()
    s.init(host)
    r.init(host)



     s.send("lalala")


    var springContext : ApplicationContext = AnnotationConfigApplicationContext("com.example.demo.model")


    var send : Prog = springContext.getBean("send") as Prog
    var recv : Prog2 = springContext.getBean("recv") as Prog2


*/
    SpringApplication.run(Model::class.java, *args)



}