package com.shvrev.studentplatform.integration.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.shvrev.studentplatform.StudentPlatformApplication;
import com.shvrev.studentplatform.entity.dto.StudentDTO;
import com.shvrev.studentplatform.integration.container.ContainerEnv;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static com.shvrev.studentplatform.integration.common.TestUtils.getClassPathResourceAsObject;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StudentPlatformApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest extends ContainerEnv {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void createStudentTest(){
        StudentDTO createdStudentDTO = createNewStudent("student1.json");
    }

    private StudentDTO createNewStudent(String filename) {
        StudentDTO studentDto = getClassPathResourceAsObject("/dto/students/" + filename, new TypeReference<>() {});
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        ResponseEntity<StudentDTO> result = testRestTemplate.exchange(
                "/api/students",
                HttpMethod.POST,
                new HttpEntity<>(studentDto, HttpHeaders.writableHttpHeaders(headers)),
                new ParameterizedTypeReference<>() {
                }
        );
        return result.getBody();
    }
}
