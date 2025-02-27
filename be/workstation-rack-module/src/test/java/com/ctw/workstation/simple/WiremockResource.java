package com.ctw.workstation.simple;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.enterprise.context.ApplicationScoped;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.quarkus.dev.ErrorPageGenerators.get;

public class WiremockResource implements QuarkusTestResourceLifecycleManager {

    WireMockServer wiremock;

    @Override
    public void init(Map<String, String> initArgs) {
        QuarkusTestResourceLifecycleManager.super.init(initArgs);
        WireMock.configureFor(new WireMock(wiremock));

    }

    @Override
    public Map<String, String> start() {
        wiremock = new WireMockServer(3001);
        //String name = "Gojo";
        //wiremock.stubFor(WireMock.get("/external/hello").withRequestBody("message", equalTo(name))
        wiremock.start();


        return Map.of("external-api.url", "http://localhost:3001");
    }

    @Override
    public void stop() {
        wiremock.shutdown();
    }

    public void exactUrlOnly() {
        String name = "\"Gojo\"";
        //wiremock.stubFor(WireMock.get("/external/hello").withRequestBody("message", equalTo(name))
        wiremock.stubFor(WireMock.get("/external/hello").withRequestBody(equalToJson("{ \"message\": " + name+ "}"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBody("Hello " + name + "!")));
    }
}
