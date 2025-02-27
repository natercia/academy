package com.ctw.workstation.team.config;

import io.quarkus.logging.Log;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.Map;

//classe q permite definir configuracoes especificas para classes de testes
public class ctwAcademyResource implements QuarkusTestResourceLifecycleManager {

    @Override
    public Map<String, String> start() {
        Log.info("Starting Quarkus Ctw Academy Resource");
        return Map.of();
        //permite reconfiguar ou sobreescrever as propriedades.
        //ex:
        //return Map.of("quarkus.log.console.json", "true");
    }

    @Override
    public void stop() {
        Log.info("Stopping Quarkus Ctw Academy Resource");

    }
}
