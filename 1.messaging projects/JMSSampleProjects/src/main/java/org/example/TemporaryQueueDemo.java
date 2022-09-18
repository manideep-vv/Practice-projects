package org.example;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;

public class TemporaryQueueDemo {
    public static void main(String[] args) throws Exception {
        InitialContext ic = new InitialContext();
        Queue q = (Queue) ic.lookup("queue/myQueue");
        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
             JMSContext context = cf.createContext();
        ) {
//           ===== set the temporary queue address to the text message
            JMSProducer producer = context.createProducer();
            TextMessage textMessage = context.createTextMessage("hello wife is from chirala");
            TemporaryQueue temporaryQueue = context.createTemporaryQueue();
            textMessage.setJMSReplyTo(temporaryQueue);
            producer.send(q, textMessage);
//            ObjectMessage o=context.createObjectMessage(p);
            Patient p=new Patient();
            p.setId(123);
            producer.send(q,p);



            JMSConsumer consumer = context.createConsumer(q);
            TextMessage receive = (TextMessage) consumer.receive(5000);
            System.out.println("This message is received in original queue  as  " + receive.getText());

            //send the new message to reply queue
            producer.send(receive.getJMSReplyTo(), "Replying as i am mani from kavali");
            //to listening from the temporary queue create a consumer on a temporary queue
            JMSConsumer tempQueueConsumer = context.createConsumer(temporaryQueue);
            String s = tempQueueConsumer.receiveBody(String.class);
            System.out.println("response in temporary queue is --> " + s);


        }

    }
}
