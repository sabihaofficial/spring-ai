package com.spring.ai.practice.demo.service;


import com.spring.ai.practice.demo.model.Answer;
import com.spring.ai.practice.demo.model.GetCapitalRequest;
import com.spring.ai.practice.demo.model.GetCapitalResponse;
import com.spring.ai.practice.demo.model.Question;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.Objects;


@Service
public class OpenAIServiceImpl implements OpenAIService {

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;

    @Value("classpath:templates/get-capital-with-info.st")
    private Resource getCapitalWithInfoPrompt;

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
        System.out.println("question : " + question.question());
        return new Answer(Objects.requireNonNull(response.getResult()).getOutput().getText());

    }

    @Override
    public GetCapitalResponse getCapital(GetCapitalRequest req) {
        //PromptTemplate promptTemplate =  new PromptTemplate("what is the capital of : " + req.countryOrState()+ " ?");

        BeanOutputConverter<GetCapitalResponse> converter =  new BeanOutputConverter<>(GetCapitalResponse.class);
        String format = converter.getFormat();

        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("countryOrState", req.countryOrState(),"format",format));
        System.out.println("Prompt: " + getCapitalPrompt);
        ChatResponse response = chatModel.call(prompt);

        //JsonNode jsonNode = new ObjectMapper().readTree(response.getResult().getOutput().getText());

        //return new Answer(Objects.requireNonNull(jsonNode.get("answer")).asText());
        return converter.convert(Objects.requireNonNull(response.getResult()).getOutput().getText());
    }

    @Override
    public Answer getCapitalWithInfo(GetCapitalRequest req) {
        //PromptTemplate promptTemplate =  new PromptTemplate("what is the capital of : " + req.countryOrState()+ " ?");
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalWithInfoPrompt);
        Prompt prompt = promptTemplate.create(Map.of("countryOrState", req.countryOrState()));
        System.out.println("Prompt: " + getCapitalPrompt);
        ChatResponse response = chatModel.call(prompt);
        return new Answer(Objects.requireNonNull(response.getResult()).getOutput().getText());
    }
}
