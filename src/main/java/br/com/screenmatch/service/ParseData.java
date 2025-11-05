package br.com.screenmatch.service;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

public class ParseData implements iParseData {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> classType) {
        try {
            return mapper.readValue(json, classType);
        } catch (JacksonException e) {
            throw new RuntimeException(e);
        }
    }
}
