package com.hooria.todo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TodoApplicationTest {

    @Test
    void contextLoads() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TodoApplication.class);
        assertThat(ac).isNotNull();
    }
}
