package com.spring.ai.practice.demo.service;


import com.spring.ai.practice.demo.model.Answer;
import com.spring.ai.practice.demo.model.GetCapitalRequest;
import com.spring.ai.practice.demo.model.GetCapitalResponse;
import com.spring.ai.practice.demo.model.Question;

public interface OpenAIService {
    String getAnswer(String question);

    Answer getAnswer(Question question);

    GetCapitalResponse getCapital(GetCapitalRequest countryOrState);

    Answer getCapitalWithInfo(GetCapitalRequest countryOrState);

}
