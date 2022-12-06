package com.assessment.assessment.dto;

import java.util.HashMap;
import java.util.Map;

public class TextResponseDto {
    private final Map<String, Integer> counts;
    public TextResponseDto() {
        this.counts = new HashMap<>();
    }
    public Map<String, Integer> getCounts() {
        return counts;
    }
}
