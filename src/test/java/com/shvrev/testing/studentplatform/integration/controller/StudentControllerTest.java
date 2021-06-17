package com.shvrev.testing.studentplatform.integration.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.shvrev.studentplatform.StudentPlatformApplication;
import com.shvrev.studentplatform.entity.dto.StudentDTO;
import com.shvrev.testing.studentplatform.integration.container.ContainerEnv;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static com.shvrev.testing.studentplatform.integration.common.TestUtils.getClassPathResourceAsObject;


@ActiveProfiles("test")
@SpringBootTest(classes = {StudentPlatformApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest extends ContainerEnv {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void createStudentTest() {
        createNewStudent("student1.json");
        List<StudentDTO> allStudents = getAllStudents();
        assertEquals(1,allStudents.size());
        assertEquals("BarakBarak", allStudents.get(0).getFirstName());
        assertEquals("Obamabama", allStudents.get(0).getLastName());
        assertEquals(LocalDate.of(1999,2,2), allStudents.get(0).getBirthDate());

    }

    private void createNewStudent(String filename) {
        StudentDTO studentDto = getClassPathResourceAsObject("/dto/students/" + filename, new TypeReference<>() {
        });
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        testRestTemplate.exchange(
                "/api/students",
                HttpMethod.POST,
                new HttpEntity<>(studentDto, HttpHeaders.writableHttpHeaders(headers)),
                new ParameterizedTypeReference<>() {
                }
        );
    }

    private List<StudentDTO> getAllStudents() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        ResponseEntity<List<StudentDTO>> list = testRestTemplate.exchange(
                "/api/students",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );
        return list.getBody();
    }
}
