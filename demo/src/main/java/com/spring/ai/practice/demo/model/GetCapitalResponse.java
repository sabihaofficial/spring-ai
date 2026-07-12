package com.spring.ai.practice.demo.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record GetCapitalResponse(@JsonPropertyDescription("The city name is ") String answer) {
}
