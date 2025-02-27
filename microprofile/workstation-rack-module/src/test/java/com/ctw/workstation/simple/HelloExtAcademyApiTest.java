package com.ctw.workstation.simple;

import com.ctw.workstation.team.config.ctwAcademyResource;
import com.ctw.workstation.team.config.ctwAcademyTestProfile;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestProfile(ctwAcademyTestProfile.class)
@QuarkusTestResource(WiremockResource.class)
class HelloExtAcademyApiTest {

    @Inject
    HelloExtAcademy helloExtAcademy;

    /*
    @Test
    @DisplayName("Saying hello to external API Get")
    void saying_hello_to_external_api_get() {
        //given
        String name = null;

        WireMock.stubFor(WireMock.get("/external/hello")
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBody("Hello!")));

        //when
        String result = helloExtAcademy.sayHello(name);
        //then
        Assertions.assertThat(result).isEqualTo("Hello!");
    }

    @Test
    @DisplayName("Saying hello to external API Post")
    void saying_hello_to_external_api_post() {
        //given
        String name = "Gojo";

        WireMock.stubFor(WireMock.post("/external/hello").withRequestBody(equalToJson("{ \"message\": \"" + name+ "\"}"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBody("Hello " + name + "!")));

        //when


        String result = helloExtAcademy.sayHello(name);
        //then
        Assertions.assertThat(result).isEqualTo("Hello " + name + "!");
    }
    */
}