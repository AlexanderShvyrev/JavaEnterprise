package com.shvrev.studentplatform.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shvrev.studentplatform.entity.db.Student;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JMSConsumer {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    @JmsListener(destination = "huemoe")
    public void consume(String message) {
        objectMapper.readValue(message, Student.class);
        System.out.println("Picked up message: " + message);
    }
}
