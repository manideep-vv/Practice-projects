package org.example;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;

/**
 * Hello world!
 *
 */
public class A1_DefaultMessageIdDemo
{
    public static void main( String[] args ) throws Exception {
        InitialContext ic=new InitialContext();
        Queue q = (Queue) ic.lookup("queue/myQueue");
        try(ActiveMQConnectionFactory cf=new ActiveMQConnectionFactory();
            JMSContext context = cf.createContext();
        ){
            JMSProducer producer = context.createProducer();
            TextMessage msg=context.createTextMessage("hello my wife is santhoshi voleti");
            System.out.println("while sending message id was auto set to "+msg.getJMSMessageID()); // By default an unique id will be set by JMS and while receiving u can check this
            producer.send(q, msg);
//            Thread.sleep(8000);
            JMSConsumer consumer = context.createConsumer(q);
            TextMessage  receivedMsg = (TextMessage) consumer.receive();
            System.out.printf("default message id is--> %s , and message is %n ",receivedMsg.getJMSMessageID());
            System.out.printf(" message is--> "+receivedMsg.getText());
        }

        System.out.println( "PROGRAM ENDED====" );
    }
}
