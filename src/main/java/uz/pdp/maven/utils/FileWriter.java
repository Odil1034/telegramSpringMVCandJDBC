package uz.pdp.maven.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public interface FileWriter {

    String BASE_URI = "C:\\Users\\bobur\\Desktop\\projects\\g40\\loginProject\\src\\main\\resources";

    static String write(byte[] bytes, String extension){
        String uuid = UUID.randomUUID().toString();
        Path resolve = Path.of(BASE_URI).resolve(uuid + "." + extension);
        try {
            Files.write(resolve,bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return uuid;
    }



}
