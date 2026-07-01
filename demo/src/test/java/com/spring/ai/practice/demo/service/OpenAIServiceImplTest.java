package com.spring.ai.practice.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OpenAIServiceImplTest {

    @Autowired
    OpenAIServiceImpl openAIService;

    @Test
    public void getAnswerTest() {
        String question = "Hey?";

        String answer = openAIService.getAnswer(question);
        System.out.println("Answer (test): '" + answer + "'");
    }


}
