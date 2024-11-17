package com.alura.convert_monedas.conexion;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangerateApi {
    private String apiUrl;
    private final HttpClient client = HttpClient.newHttpClient();

    public ExchangerateApi() {
        apiUrl = "https://v6.exchangerate-api.com/v6/76c9d3ccfffc45aeea71988d/latest/";
    }

    public HttpResponse<String> getRequest(String CodigoMoneda) {
        //noinspection ResultOfMethodCallIgnored
        CodigoMoneda.replace(" ", "+");
        apiUrl += CodigoMoneda;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .build();

        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
