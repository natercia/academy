package com.ctw.workstation.simple;

import jdk.jshell.spi.ExecutionControl;
import org.apache.commons.lang3.NotImplementedException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HelloExtAcademyTest {

    @Mock
    ExternalMessageService externalMessageService;

    @InjectMocks
    HelloExtAcademy helloExtAcademy;

    /*
    @Test
    void lista() {
        ArrayList<String> minhaLista = new ArrayList<>();
        ArrayList<String> minhaListaSpy = Mockito.spy(minhaLista);

        //Mockito.when(minhaListaSpy.get(0)).thenReturn("Hello World");
        Mockito.doReturn("Hello World").when(minhaListaSpy).get(0);

    }

     */

    /*
    @Test
    public void hello_from_outer_space(){
        //given
        String name = "Gojo";
        //HelloExtAcademy helloExtAcademy = new HelloExtAcademy();

        //spy config
        /*
        ExternalMessageServiceImpl externalMessageService = new ExternalMessageServiceImpl();
        ExternalMessageService  externalMessageServiceSpy = Mockito.spy(externalMessageService);
        helloExtAcademy.ems = externalMessageServiceSpy;

        Mockito.doReturn("Hello World").when(externalMessageServiceSpy).sayHelloFromOuterSpace();
        */

        //mock config
        //ExternalMessageService emsMock = Mockito.mock(ExternalMessageService.class);
        //helloExtAcademy.ems = externalMessageService;
/*

        Mockito.when(externalMessageService.sayHelloFromOuterSpace())
                .thenThrow(new NotImplementedException("Não há."));
                //.thenReturn("Hello from outer space");
                        //.thenAnswer(i -> "Hello from outer space");
        //Mockito.when(emsMock.sayHelloFromOuterSpace(Mockito.eq(name))).thenReturn("Hello %s from outer space".formatted(name));

        //when
        String result = helloExtAcademy.sayHello(name);

        //then
        Assertions.assertThat(result)
                .isEqualTo("Hello %s from outer space".formatted(name));
    }
    */

}