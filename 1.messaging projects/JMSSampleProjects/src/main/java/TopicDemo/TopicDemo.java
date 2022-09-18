package TopicDemo;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.example.Patient;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TopicDemo {
    public static void main(String[] args) throws Exception {
        InitialContext ic =new InitialContext();
        Topic topic = (Topic) ic.lookup("topic/myTopic");
        try(ActiveMQConnectionFactory cf=new ActiveMQConnectionFactory();
            JMSContext context = cf.createContext();){
            JMSProducer producer = context.createProducer();
            Patient p = new Patient();
            p.setName("charan");

            producer.send(topic, p);

            JMSConsumer consumer = context.createConsumer(topic);
            Message receive = consumer.receive();
            Patient patient = receive.getBody(Patient.class);
            System.out.println("received patient name-->"+patient.getName());
        }


    }
}
