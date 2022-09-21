package org.example;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;

/**
 * not working some issue in consuming
 */
public class MessageFilteringDoctorDemo {
    public static void main(String[] args) throws Exception {

        InitialContext ic=new InitialContext();
        Queue requestQueue = (Queue) ic.lookup("queue/claimQueue");
        try(ActiveMQConnectionFactory cf=new ActiveMQConnectionFactory();
            JMSContext jmsContext = cf.createContext();
        ){
            JMSProducer producer = jmsContext.createProducer();
            JMSConsumer consumer = jmsContext.createConsumer(requestQueue,"specialist IN ('kidney','hair','heart')");
//            JMSConsumer consumer = jmsContext.createConsumer(requestQueue);

            ObjectMessage objectMessage = jmsContext.createObjectMessage();
            objectMessage.setStringProperty("specialist","kidney");
            Doctor d = new Doctor("Santhosh", "Kidney");
            objectMessage.setObject(d);
            d.setDoctorName("santhosham");

            producer.send(requestQueue,objectMessage);
            System.out.println("waiting for the message");
            Doctor doctor = consumer.receiveBody(Doctor.class);
             consumer.receive();
            System.out.printf("received the message with doctor name %s ",doctor.getDoctorName());


        }

    }
}
