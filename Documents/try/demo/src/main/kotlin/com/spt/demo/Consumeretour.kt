package com.spt.demo

import com.rabbitmq.client.*;
import java.nio.charset.Charset

class Consumeretour  ( channel : Channel, val send : Send) : DefaultConsumer(channel) {

    override fun handleDelivery(consumerTag : String , envelope : Envelope , properties : AMQP.BasicProperties, body : ByteArray) {

        var message = body.toString(Charset.defaultCharset())
        View.prompt(message)
        send.close()


    }




}