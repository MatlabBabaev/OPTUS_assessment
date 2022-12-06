package com.assessment.assessment.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TextRepository extends HashMap<String, Integer> {

    private final Map<String, Integer> map;
    private final Environment env;


    @Autowired
    public TextRepository(Map<String, Integer> map, Environment env) {
        this.map = map;
        this.env = env;
    }

    @PostConstruct
    public void populate() throws FileNotFoundException {
        Scanner cs = new Scanner(new File(Objects.requireNonNull(env.getProperty("file.location"))));
        String s = cs.next()
                .replace(".", " ")
                .replace(",", " ")
                .replace(";", " ")
                .trim().toLowerCase();
        while (s != null) {
            if (map.isEmpty() || !map.containsKey(s))
                map.put(s, 1);
            else
                map.put(s, map.get(s) + 1);
            s = cs.hasNext() ? cs.next().replace(".", " ")
                    .replace(",", " ")
                    .replace(";", " ")
                    .trim().toLowerCase() : null;
        }
    }

    public Map<String, Integer> getMap() {
        return this.map;
    }
    public Map<String, Integer> getTopN(int n) {
        Map<String, Integer> topN = map.entrySet().stream()
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .limit(n)
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));


        return topN;
    }
}
