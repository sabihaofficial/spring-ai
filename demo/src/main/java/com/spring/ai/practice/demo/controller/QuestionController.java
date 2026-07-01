package com.spring.ai.practice.demo.controller;

import com.spring.ai.practice.demo.model.Answer;
import com.spring.ai.practice.demo.model.GetCapitalRequest;
import com.spring.ai.practice.demo.model.Question;
import com.spring.ai.practice.demo.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {


    @Autowired
    OpenAIService openAIService;

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        Answer answer = openAIService.getAnswer(question);
        return answer;
    }

    @PostMapping("/capital")
    public Answer getCapital(@RequestBody GetCapitalRequest request) {
        Answer answer = openAIService.getCapital(request);
        return answer;
    }



}
