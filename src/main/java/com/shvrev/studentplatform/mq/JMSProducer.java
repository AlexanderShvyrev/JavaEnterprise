package com.shvrev.studentplatform.mq;


import com.shvrev.studentplatform.entity.db.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JMSProducer {
    @Autowired
    JmsTemplate jmsTemplate;

    @Value("huemoe")
    private String topic;

    public void sendMessage(Student message){
        try{
            jmsTemplate.convertAndSend(topic, message);
        } catch(Exception e){
            System.out.println(e);
        }
    }

}
