package com.advent;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class TestBase {

    protected List<String> loadInputResource(String resourcePath) throws IOException {
        Path path = Paths.get("src/test/resources/" + resourcePath);
        return Files.readAllLines(path, StandardCharsets.UTF_8).stream().collect(Collectors.toList());
    }
}
