package com.spt.demo

import com.rabbitmq.client.*;
import java.nio.charset.Charset


class Consumer ( channel : Channel, val recv : Recv) : DefaultConsumer(channel) {
    override fun handleDelivery(consumerTag : String , envelope : Envelope , properties : AMQP.BasicProperties, body : ByteArray) {

        var message = body.toString(Charset.defaultCharset())

        recv.send(message)

    }
}