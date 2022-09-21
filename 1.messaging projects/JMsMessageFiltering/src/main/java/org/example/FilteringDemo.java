package org.example;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Enumeration;

/**
 * Hello world!
 *
 */
public class FilteringDemo
{
    public static void main(String[] args) throws NamingException, JMSException {
        InitialContext initialContext = new InitialContext();
        Queue requestQueue = (Queue) initialContext.lookup("queue/claimQueue");

        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
             JMSContext jmsContext = cf.createContext()) {
            JMSProducer producer = jmsContext.createProducer();
//            JMSConsumer consumer = jmsContext.createConsumer(requestQueue, "doctorType IN ('neuro','psych') OR JMSPriority BETWEEN 3 AND 9");
            JMSConsumer consumer = jmsContext.createConsumer(requestQueue,"hospitalName IN ('Apollo','om')");
//            JMSConsumer consumer = jmsContext.createConsumer(requestQueue);

            ObjectMessage objectMessage = jmsContext.createObjectMessage();
            //objectMessage.setIntProperty("hospitalId", 1);
            //objectMessage.setDoubleProperty("claimAmount", 1000);
            //objectMessage.setStringProperty("doctorName", "John");
            objectMessage.setStringProperty("battay", "om");
            Claim claim = new Claim();
            claim.setHospitalId(1);
            claim.setClaimAmount(1009);
            claim.setDoctorName("John");
            claim.setDoctorType("gyna");
            claim.setInsuranceProvider("blue cross");
            objectMessage.setObject(claim);

            producer.send(requestQueue, objectMessage);
            QueueBrowser browser = jmsContext.createBrowser(requestQueue);
            Enumeration enumeration = browser.getEnumeration();
            while(enumeration.hasMoreElements()){
                Object o = enumeration.nextElement();
                System.out.println(o);

            }

            System.out.println("going to wait for message");
            Claim receiveBody = consumer.receiveBody(Claim.class);
            System.out.println(receiveBody.getClaimAmount());
        }
    }
}
