package com.assessment.assessment.service;

import com.assessment.assessment.dto.TextRequestDto;
import com.assessment.assessment.dto.TextResponseDto;
import com.assessment.assessment.exception.InvalidArgumentException;
import com.assessment.assessment.repository.TextRepository;
import com.assessment.assessment.util.MapToCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CountServiceImpl implements CountService {
    private final TextRepository repo;

    @Autowired
    public CountServiceImpl(TextRepository repo) {
        this.repo = repo;
    }

    @Override
    public TextResponseDto count(TextRequestDto body) {
        TextResponseDto res = new TextResponseDto();
        List<String> search = body.getSearchText();
        Map<String, Integer> db = repo.getMap();

        if (body.getSearchText().isEmpty())
            throw new InvalidArgumentException("Empty body");

        search.forEach(s -> {
            res.getCounts().put(s, db.getOrDefault(s.toLowerCase(), 0));
        });
        return res;
    }

    @Override
    public Map<String, Integer> getTopN(String topN) {
        int i ;
        try {
            i = Integer.parseInt(topN);
        } catch (Exception ex) {
            throw new InvalidArgumentException("Invalid argument! Only arguments 5,10,20,30 are allowed");
        }
        Map<String, Integer> top = repo.getTopN(i);
        try {
            MapToCSV.convertToSCV(top);
        } catch (Exception e) {
            System.out.println("Error!!!");
        }
        return top;
    }
}
