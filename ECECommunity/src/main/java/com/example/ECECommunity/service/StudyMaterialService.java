package com.example.ECECommunity.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Service
public class StudyMaterialService {

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.service-role-key}")
    private String serviceRoleKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Map<String, String>> getVlsiMaterial() {

        String bucketName = "Embedded Systems, IoT & VLSI";
        String fileName = "Introduction to VLSI.pdf";

        // Create Supabase signed URL endpoint
        String signUrl = UriComponentsBuilder
                .fromUriString(supabaseUrl)
                .path("/storage/v1/object/sign/")
                .pathSegment(bucketName)
                .pathSegment(fileName)
                .build()
                .toUriString();

        HttpHeaders headers = new HttpHeaders();

        headers.setBearerAuth(serviceRoleKey);
        headers.set("apikey", serviceRoleKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of(
                "expiresIn", 3600
        );

        HttpEntity<Map<String, Object>> request =
                new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                signUrl,
                HttpMethod.POST,
                request,
                Map.class
        );

        Map responseBody = response.getBody();

        if (responseBody == null ||
                responseBody.get("signedURL") == null) {

            throw new RuntimeException(
                    "Supabase did not return signed URL: "
                            + responseBody
            );
        }

        String signedPath =
                responseBody.get("signedURL").toString();

        String finalUrl;

        if (signedPath.startsWith("http")) {
            finalUrl = signedPath;
        } else {
            finalUrl = supabaseUrl
                    + "/storage/v1"
                    + signedPath;
        }

        return List.of(
                Map.of(
                        "name", fileName,
                        "url", finalUrl
                )
        );
    }
}