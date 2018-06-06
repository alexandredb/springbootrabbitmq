package com.spt.demo

import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DefaultConsumer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Required
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service


@Service()
class Recv() : Prog2 {
    val QUEUE_ALLER :String = "aller"
    var factory: ConnectionFactory =ConnectionFactory()
    val QUEUE_RETOUR="retour"
    var connection : Connection?=null
    var channel_envoi : Channel ?=null
    var channel_reception : Channel ? = null
    var consumer : DefaultConsumer?=null


    @Autowired
    @Required
    override fun init() {
        factory.setHost(Model.host)
        connection = factory.newConnection()
        channel_envoi=connection!!.createChannel()
        channel_reception= connection!!.createChannel()
        channel_envoi!!.queueDeclare(QUEUE_RETOUR,false,false,false,null)
        channel_reception!!.queueDeclare(QUEUE_ALLER,false,false,false,null)
        consumer = Consumer(channel_reception!!,this)
        println("recv")
        channel_reception!!.basicConsume(QUEUE_ALLER, true, consumer)

    }

    override fun send (message : String){
        channel_reception!!.basicPublish("",QUEUE_RETOUR, null , message.toByteArray())
        close()
    }

    override fun close(){
        if(channel_envoi!= null)channel_envoi!!.close()
        if(channel_reception!= null) channel_reception!!.close()
        connection!!.close()
    }

}