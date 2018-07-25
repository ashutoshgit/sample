package com.ashu.springbootproject.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MyRepository {

    private Map<String, String> map = new HashMap<>();

    public String save(String key, String value) {
        map.put(key,value);
        return "Saved";
    }

    public String get(String key) {
        return map.get(key);
    }

    public List<String> getAll() {
        return map.values().stream().collect(Collectors.toList());
    }

    public String update(String key, String value) {
        map.put(key,value);
        return "Updated";
    }

    public String delete(String key) {
        map.remove(key);
        return "Saved";
    }
}
