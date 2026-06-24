package org.scoula.sample.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.scoula.advice.LogAdvice;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SampleServiceTest {
    @Configuration
    @EnableAspectJAutoProxy
    @Import({SampleServiceImpl.class, LogAdvice.class})
    static class TestConfig {
    }

    private AnnotationConfigApplicationContext context;
    private SampleService sampleService;
    private PrintStream originalOut;
    private ByteArrayOutputStream output;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext();
        context.register(TestConfig.class);
        context.refresh();
        sampleService = context.getBean(SampleService.class);

        originalOut = System.out;
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        context.close();
    }

    @Test
    void serviceIsAopProxyAndAddsNumbers() throws Exception {
        assertTrue(AopUtils.isAopProxy(sampleService));
        assertEquals(7, sampleService.doAdd("3", "4"));

        String log = output.toString(StandardCharsets.UTF_8);
        assertTrue(log.contains("========================================"));
        assertTrue(log.contains("str1: 3"));
        assertTrue(log.contains("str2: 4"));
    }

    @Test
    void invalidNumberStillRunsBeforeAdviceAndThrows() {
        assertThrows(NumberFormatException.class,
                () -> sampleService.doAdd("not-a-number", "4"));

        String log = output.toString(StandardCharsets.UTF_8);
        assertTrue(log.contains("str1: not-a-number"));
        assertTrue(log.contains("str2: 4"));
    }
}
