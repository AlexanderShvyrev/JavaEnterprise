package com.shvrev.studentplatform.mq;

import com.shvrev.studentplatform.entity.db.Student;
import lombok.SneakyThrows;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;

@Component
public class JMSConsumer implements MessageListener {
//    @JmsListener(destination = "huemoe", containerFactory = "jmsListenerContainerFactory")
//    public void consume(Student message) {
//        System.out.println("Picked up message: " + message);
//    }

    @SneakyThrows
    @Override
    public void onMessage(Message message) {
        System.out.println(message.getBody(String.class));
    }
}
