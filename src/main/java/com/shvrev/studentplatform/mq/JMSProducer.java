package com.shvrev.studentplatform.mq;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.shvrev.studentplatform.entity.db.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JMSProducer {
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;
    @Value("huemoe")
    private String topic;

    public void sendMessage(Student message){
        try{
            jmsTemplate.convertAndSend(topic, objectMapper.writeValueAsString(message));
        } catch(Exception e){
            System.out.println(e);
        }
    }

}
