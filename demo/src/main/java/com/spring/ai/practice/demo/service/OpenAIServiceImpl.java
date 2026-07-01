package com.spring.ai.practice.demo.service;


import com.spring.ai.practice.demo.model.Answer;
import com.spring.ai.practice.demo.model.GetCapitalRequest;
import com.spring.ai.practice.demo.model.Question;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;


@Service
public class OpenAIServiceImpl implements OpenAIService {

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;
    private final ChatModel chatModel;

    public OpenAIServiceImpl(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public String getAnswer(String question) {
        PromptTemplate promptTemplate =  new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatModel.call(prompt);
        if (response != null) {
            return Objects.requireNonNull(response.getResult()).getOutput().getText();
        }
        return "";
    }

    @Override
    public Answer getAnswer(Question question) {
        PromptTemplate promptTemplate =  new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatModel.call(prompt);
        return new Answer(Objects.requireNonNull(response.getResult()).getOutput().getText());

    }

    @Override
    public Answer getCapital(GetCapitalRequest req) {
        //PromptTemplate promptTemplate =  new PromptTemplate("what is the capital of : " + req.countryOrState()+ " ?");
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("countryOrState", req.countryOrState()));
        System.out.println("Prompt: " + getCapitalPrompt);
        ChatResponse response = chatModel.call(prompt);
        return new Answer(Objects.requireNonNull(response.getResult()).getOutput().getText());
    }
}
