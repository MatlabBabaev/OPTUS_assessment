package com.assessment.assessment.service;

import com.assessment.assessment.dto.TextRequestDto;
import com.assessment.assessment.dto.TextResponseDto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

public interface CountService {

    TextResponseDto count(TextRequestDto text) throws FileNotFoundException;
    Map<String, Integer> getTopN(String topN) throws IOException;
}
