package com.assessment.assessment.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class MapToCSV {

    public static void convertToSCV(Map<String, Integer> data) throws IOException {
        FileWriter out = new FileWriter("Result.csv");
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT
        )) {
            data.forEach((text, count) -> {
                try {
                    printer.printRecord(text, count);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
