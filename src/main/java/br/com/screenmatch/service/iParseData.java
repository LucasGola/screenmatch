package br.com.screenmatch.service;

public interface iParseData {
    <T> T getData(String json, Class<T> classType);
}
