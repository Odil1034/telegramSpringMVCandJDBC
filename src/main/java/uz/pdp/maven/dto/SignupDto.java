package uz.pdp.maven.dto;

import jakarta.servlet.http.Part;

public record SignupDto(
        String name,
        String username,
        String email,
        Integer age,
        Part upload,
        String password,
        String confirmPassword
) {
}

