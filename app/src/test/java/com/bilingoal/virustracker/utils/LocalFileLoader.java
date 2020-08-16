package com.bilingoal.virustracker.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LocalFileLoader {
    public static String readFileFromResources(String filename) throws URISyntaxException, IOException {
        URL resource = LocalFileLoader.class.getClassLoader().getResource(filename);
        byte[] bytes = Files.readAllBytes(Paths.get(resource.toURI()));
        return new String(bytes);
    }
}
