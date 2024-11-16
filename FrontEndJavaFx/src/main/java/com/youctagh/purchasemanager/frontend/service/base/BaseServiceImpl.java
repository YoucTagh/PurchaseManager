package com.youctagh.purchasemanager.frontend.service.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import static java.net.URI.create;
import static java.net.http.HttpResponse.BodyHandlers.ofString;

/**
 * @author YoucTagh
 */
public class BaseServiceImpl implements BaseService {
    private final String BASE_URL = "http://localhost:8080/api/v1";

    @Override
    public Optional findById(Long id, String relativeURL, Class clazz) {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .uri(create(BASE_URL + relativeURL + "/" + id))
                .method("GET", (HttpRequest.BodyPublishers.noBody()))
                .build();
        AtomicReference<Object> answer = new AtomicReference<>();
        client.sendAsync(request, ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(s -> {
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode jsonNode = mapper.readTree(s);
                        answer.set(mapper.readValue(jsonNode.get("payload").toPrettyString(), clazz));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                })
                .join();
        return Optional.of(answer.get());
    }

    @Override
    public HashSet findAll(String relativeURL, Class clazz) {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .uri(create(BASE_URL + relativeURL))
                .method("GET", (HttpRequest.BodyPublishers.noBody()))
                .build();
        AtomicReference<HashSet> answer = new AtomicReference();
        client.sendAsync(request, ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(s -> {
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode jsonNode = mapper.readTree(s);
                        answer.set(mapper.readValue(jsonNode.get("payload").toPrettyString(), mapper.getTypeFactory().constructCollectionType(HashSet.class, clazz)));       
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                })
                .join();
        return answer.get();
    }

    @Override
    public Optional addObject(Object object, String relativeURL, Class clazz) {
        ObjectMapper mapper = new ObjectMapper();
        var client = HttpClient.newHttpClient();
        AtomicReference<Object> answer = new AtomicReference<>();
        byte[] sampleData = new byte[0];
        try {
            sampleData = mapper.writeValueAsString(object).getBytes();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        var request = HttpRequest.newBuilder()
                .uri(create(BASE_URL + relativeURL))
                .headers("Content-Type", "application/json")
                .method("POST", (HttpRequest.BodyPublishers.ofByteArray(sampleData)))
                .build();
        client.sendAsync(request, ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(s -> {
                    try {
                        JsonNode jsonNode = mapper.readTree(s);
                        String getSth = jsonNode.get("payload").toPrettyString();
                        answer.set(mapper.readValue(getSth.toString(), clazz)); 
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                })
                .join();
        return Optional.of(answer.get());
    }

    @Override
    public Optional deleteObject(Object object, String relativeURL, Class clazz) {
        ObjectMapper mapper = new ObjectMapper();
        var client = HttpClient.newHttpClient();
        AtomicReference<Object> answer = new AtomicReference<>();
        byte[] sampleData = new byte[0];
        try {
            sampleData = mapper.writeValueAsString(object).getBytes();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        var request = HttpRequest.newBuilder()
                .uri(create(BASE_URL + relativeURL))
                .headers("Content-Type", "application/json")
                .method("DELETE", (HttpRequest.BodyPublishers.ofByteArray(sampleData)))
                .build();
        client.sendAsync(request, ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(s -> {
                    try {
                        JsonNode jsonNode = mapper.readTree(s);
                        String getSth = jsonNode.get("payload").toPrettyString();
                        answer.set(mapper.readValue(getSth.toString(), clazz)); 
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                })
                .join();
        return Optional.of(answer.get());
    }

    @Override
    public Optional updateObject(Object object, String relativeURL, Class clazz) {
        ObjectMapper mapper = new ObjectMapper();
        var client = HttpClient.newHttpClient();
        AtomicReference<Object> answer = new AtomicReference<>();
        byte[] sampleData = new byte[0];
        try {
            sampleData = mapper.writeValueAsString(object).getBytes();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        var request = HttpRequest.newBuilder()
                .uri(create(BASE_URL + relativeURL))
                .headers("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofByteArray(sampleData))
                .build();
        client.sendAsync(request, ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(s -> {
                    try {
                        JsonNode jsonNode = mapper.readTree(s);
                        String getSth = jsonNode.get("payload").toPrettyString();
                        answer.set(mapper.readValue(getSth.toString(), clazz)); 
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                })
                .join();
        return Optional.of(answer.get());
    }




}
