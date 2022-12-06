package com.assessment.assessment.controllers;

import com.assessment.assessment.dto.TextRequestDto;
import com.assessment.assessment.dto.TextResponseDto;
import com.assessment.assessment.exception.InvalidArgumentException;
import com.assessment.assessment.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/counter-api")
public class CountsController {

    private final CountService service;

    @Autowired
    public CountsController(CountService service) {
        this.service = service;
    }

    @PostMapping(path = "/search", consumes = {MediaType.APPLICATION_JSON_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TextResponseDto> search(@Valid @RequestBody TextRequestDto requestDto) throws FileNotFoundException {

        return new ResponseEntity<>(service.count(requestDto), HttpStatus.OK);
    }

    @GetMapping(path = "/top/{topN}")
    public ResponseEntity<String> getTop(@PathVariable String topN) throws IOException {
        if(!(topN.equals("5") || topN.equals("10") || topN.equals("20") || topN.equals("30")))
            throw new InvalidArgumentException("Invalid argument " + topN + ". Only 5,10,20,30 are allowed!");
        service.getTopN(topN);
        return new ResponseEntity<>("Result saved into file: Result.csv!", HttpStatus.OK);

    }
}
