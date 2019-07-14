package com.egiant.appointment.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.egiant.appointment.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper = newObjectMapper();
    }

    @Test
    public void getUserById() throws IOException {

        int userId = 1;

        UserDto userDto = UserDto.builder()
                .userId(userId)
                .appointmentDttm("02-02-2019:09.10 AM")
                .dob("02-01-2010")
                .firstName("Test")
                .lastName("TestL")
                .build();

        String url = String.format("http://localhost:%d/user/saveUser", this.port);
        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<JsonNode> response  = testRestTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(objectMapper.readTree(objectMapper.writeValueAsString(userDto)), headers), JsonNode.class);
        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.OK)));

         url = String.format("http://localhost:%d/user/getUser/1", this.port);

        response  =  testRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(objectMapper.readTree(objectMapper.writeValueAsString(userDto)), headers), JsonNode.class);
        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.OK)));
        assertThat(response.getBody(), is(notNullValue()));
        assertThat(response.getBody().get("userId").asInt(), is(1));
        assertThat(response.getBody().get("firstName").asText(), is("Test"));
        assertThat(response.getBody().get("lastName").asText(), is("TestL"));
        assertThat(response.getBody().get("dob").asText(), is("02-01-2010"));
        assertThat(response.getBody().get("appointmentDttm").asText(), is("02-02-2019:09.10 AM"));
    }

    @Test
    public void getAllUsers() throws IOException {
        int userId = 1;

        UserDto userDto = UserDto.builder()
                .userId(userId)
                .appointmentDttm("02-02-2019:09.10 AM")
                .dob("02-01-2010")
                .firstName("Test")
                .lastName("TestL")
                .build();

        String url = String.format("http://localhost:%d/user/saveUser", this.port);
        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<JsonNode> response  = testRestTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(objectMapper.readTree(objectMapper.writeValueAsString(userDto)), headers), JsonNode.class);
        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.OK)));

        url = String.format("http://localhost:%d/user/getAllUsers", this.port);

        response  =  testRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(objectMapper.readTree(objectMapper.writeValueAsString(userDto)), headers), JsonNode.class);
        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.OK)));
        assertThat(response.getBody(), is(notNullValue()));
        assertThat(response.getBody().get(0).get("userId").asInt(), is(1));
        assertThat(response.getBody().get(0).get("firstName").asText(), is("Test"));
        assertThat(response.getBody().get(0).get("lastName").asText(), is("TestL"));
        assertThat(response.getBody().get(0).get("dob").asText(), is("02-01-2010"));
        assertThat(response.getBody().get(0).get("appointmentDttm").asText(), is("02-02-2019:09.10 AM"));
    }

    @Test
    public void saveUser() throws IOException {
        int userId = 1;

        UserDto userDto = UserDto.builder()
                .userId(userId)
                .appointmentDttm("02-02-2019:09.10 AM")
                .dob("02-01-2010")
                .firstName("Test")
                .lastName("TestL")
                .build();

        String url = String.format("http://localhost:%d/user/saveUser", this.port);
        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<JsonNode> response =   testRestTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(objectMapper.readTree(objectMapper.writeValueAsString(userDto)), headers), JsonNode.class);
        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.OK)));
    }


    public static ObjectMapper newObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        SimpleModule customSerializersModule = new SimpleModule();

        objectMapper.registerModule(new JavaTimeModule())
                .registerModule(new Jdk8Module())
                .registerModule(customSerializersModule)
                .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
                .disable(WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)
                .disable(WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }
}
