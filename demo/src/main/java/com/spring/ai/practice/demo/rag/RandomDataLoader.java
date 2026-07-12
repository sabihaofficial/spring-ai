package com.spring.ai.practice.demo.rag;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class RandomDataLoader {

    private static final List<String> SAMPLE_FACTS = List.of(
            "The Great Wall of China is over 13,000 miles long.",
            "Octopuses have three hearts and blue blood.",
            "Honey never spoils if stored properly.",
            "The Eiffel Tower can grow taller in summer due to thermal expansion.",
            "A group of flamingos is called a flamboyance.",
            "Bananas are berries, but strawberries are not.",
            "The Sahara Desert was once a lush, green landscape.",
            "Sharks existed before trees appeared on Earth.",
            "Venus is the hottest planet in the solar system.",
            "A single cloud can weigh more than a million pounds."
    );

    private final VectorStore vectorStore;

    public RandomDataLoader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void loadRandomData() {
        List<Document> documents = SAMPLE_FACTS.stream()
                .map(fact -> new Document(UUID.randomUUID().toString(), fact, Map.of("source", "random-data-loader")))
                .toList();
        vectorStore.add(documents);
    }
}
