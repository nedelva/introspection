package com.example;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.exceptions.HttpClientException;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.http.uri.UriTemplate;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    private static EmbeddedServer server;
    private static HttpClient client;

    @BeforeAll
    public static void setupServer() {
        server = ApplicationContext.run(EmbeddedServer.class);
        client = server
                .getApplicationContext()
                .createBean(HttpClient.class, server.getURL());
    }

    @AfterAll
    static void stopServer() {
        if(server !=null) {
            server.stop();
        }
        if(client !=null) {
            client.stop();
        }
    }
    @Test
    void echoInvalidArgs() {
        UriTemplate template = new UriTemplate("/api/echo{?max,min,accountNumber}");
        Map params = Map.of("max",5,"min",0, "accountNumber", "0012345678");
        String expanded = template.expand(params);
        System.out.println("expanded uri = " + expanded);
        HttpResponse<Object> response = null;
        try {
            response = client.toBlocking().exchange(expanded);
            Assertions.fail("http client should have thrown; 'min' argument is not valid");
        } catch (HttpClientResponseException e) {
            Assertions.assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
        }

    }

    @Test
    void echo() {
        UriTemplate template = new UriTemplate("/api/echo{?max,min,accountNumber}");
        int max = 20;
        int min = 2;
        String acctNumber = "0012345678";
        Map params = Map.of("max", max,"min", min, "accountNumber", acctNumber);
        String expanded = template.expand(params);
        System.out.println("expanded uri = " + expanded);
        HttpResponse<Object> response = null;
        try {
            response = client.toBlocking().exchange(expanded);
            Assertions.assertEquals(HttpStatus.OK, response.getStatus());
            String expected = String.format("{\"min\": \"%d\",\"max:\":\"%d\",\"accountNumber\":\"%s\"}", min, max, acctNumber);
            Assertions.assertEquals(expected, response.getBody(String.class).get());
        } catch (HttpClientResponseException e) {
            Assertions.fail("test should pass!");
        }
    }

}