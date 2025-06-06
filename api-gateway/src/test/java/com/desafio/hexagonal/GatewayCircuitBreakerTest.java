package com.desafio.hexagonal;

import static org.mockserver.model.HttpResponse.response;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.mockserver.client.MockServerClient;
import org.mockserver.matchers.Times;
import org.mockserver.model.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class GatewayCircuitBreakerTest {


    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayCircuitBreakerTest.class);

    @Container
    public static MockServerContainer mockServer = new MockServerContainer(DockerImageName.parse("mockserver/mockserver:5.15.0"));

    @Autowired
    TestRestTemplate template;
    final Random random = new Random();
    int i = 0;

    @BeforeAll
    public static void init() {
        System.setProperty("spring.cloud.gateway.routes[0].id", "account-service");
        System.setProperty("spring.cloud.gateway.routes[0].uri", "http://" + mockServer.getHost() + ":" + mockServer.getServerPort());
        System.setProperty("spring.cloud.gateway.routes[0].predicates[0]", "Path=/account/**");
        System.setProperty("spring.cloud.gateway.routes[0].filters[0]", "RewritePath=/account/(?<path>.*), /$\\{path}");
        System.setProperty("spring.cloud.gateway.routes[0].filters[1].name", "CircuitBreaker");
        System.setProperty("spring.cloud.gateway.routes[0].filters[1].args.name", "exampleSlowCircuitBreaker");
        System.setProperty("spring.cloud.gateway.routes[0].filters[1].args.fallbackUri", "forward:/fallback/account");
        MockServerClient client = new MockServerClient(mockServer.getContainerIpAddress(), mockServer.getServerPort());
        client.when(HttpRequest.request()
                        .withPath("/1"))
                .respond(response()
                        .withBody("{\"id\":1,\"number\":\"1234567890\"}")
                        .withHeader("Content-Type", "application/json"));
        client.when(HttpRequest.request()
                        .withPath("/2"), Times.exactly(5))
                .respond(response()
                        .withBody("{\"id\":2,\"number\":\"1234567891\"}")
                        .withDelay(TimeUnit.MILLISECONDS, 200)
                        .withHeader("Content-Type", "application/json"));
        client.when(HttpRequest.request()
                        .withPath("/2"))
                .respond(response()
                        .withBody("{\"id\":2,\"number\":\"1234567891\"}")
                        .withHeader("Content-Type", "application/json"));
    }


}