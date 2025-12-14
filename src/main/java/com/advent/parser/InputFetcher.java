package com.advent.parser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputFetcher {

    private static final String AOC_SESSION_ENV = "AOC_SESSION";

    public static String fetchRaw(String url) throws IOException, InterruptedException {
        try {
            URI uri = new URI(url);
            String scheme = uri.getScheme();
            if (scheme == null || scheme.equals("file")) {
                // treat as local file path
                Path path = scheme == null ? Paths.get(url) : Paths.get(uri);
                return Files.readString(path);
            }

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest.Builder builder = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET();

            String session = System.getenv(AOC_SESSION_ENV);
            if (session != null && !session.isBlank()) {
                builder.header("Cookie", "session=" + session);
            }

            HttpRequest request = builder.build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                return response.body();
            }

            throw new IOException("HTTP request failed with status " + response.statusCode() + " and body: " + response.body());
        } catch (URISyntaxException e) {
            // treat as local file path if broken URI
            Path path = Paths.get(url);
            return Files.readString(path);
        }
    }

    public static List<String> fetchLines(String url) throws IOException, InterruptedException {
        String raw = fetchRaw(url);
        return Arrays.stream(raw.split("\r?\n")).collect(Collectors.toList());
    }

}
