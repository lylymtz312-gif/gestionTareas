package com.gestiosTares.gestionTareas.taskmanager.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalApiService {

    private final RestTemplate restTemplate;

    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchExternalStatus() {
        try {
            String url = "https://jsonplaceholder.typicode.com/todos/1";
            // Consumo de API externa
            String response = restTemplate.getForObject(url, String.class);
            return "API Externa activa: " + response;
        } catch (RestClientException e) {
            // Manejo de caso en que la API externa no responda
            return "Advertencia: La API externa no se encuentra disponible en este momento.";
        }
    }
}