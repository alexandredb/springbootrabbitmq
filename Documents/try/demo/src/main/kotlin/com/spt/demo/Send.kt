package com.spt.demo

import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DefaultConsumer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Required
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service


@Component
class Send () : Prog{
    val QUEUE_ALLER :String = "aller"
    val QUEUE_RETOUR="retour"
    var factory: ConnectionFactory  =ConnectionFactory()
    var connection : Connection?=null
    var channel_envoi : Channel ?=null
    var channel_reception : Channel ? = null

    var consumer : DefaultConsumer? = null

    @Required
    @Autowired
    override fun init() {
        factory.setHost(Model.host)
        connection = factory!!.newConnection()
        channel_envoi=connection!!.createChannel()
        channel_reception= connection!!.createChannel()
        channel_envoi!!.queueDeclare(QUEUE_ALLER,false,false,false,null)
        channel_reception!!.queueDeclare(QUEUE_RETOUR,false,false,false,null)
        consumer = Consumeretour(channel_reception!!,this)
        println("send")
        channel_reception!!.basicConsume(QUEUE_RETOUR, true, consumer)

    }

    override fun send (message : String) =  channel_envoi!!.basicPublish("", QUEUE_ALLER, null, message.toByteArray())

    override fun close(){
        if(channel_envoi!= null)channel_envoi!!.close()
        if(channel_reception!= null) channel_reception!!.close()
        connection!!.close()
    }

}