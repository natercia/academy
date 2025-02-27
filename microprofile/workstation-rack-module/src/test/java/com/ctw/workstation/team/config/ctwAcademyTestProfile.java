package com.ctw.workstation.team.config;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.List;

//classe que permite definir perfis (conjuntos de resources) para classes de testes
public class ctwAcademyTestProfile implements QuarkusTestProfile {
    @Override
    public List<TestResourceEntry> testResources() {
        return List.of(
                new TestResourceEntry(ctwAcademyResource.class)
        );
    }
}
