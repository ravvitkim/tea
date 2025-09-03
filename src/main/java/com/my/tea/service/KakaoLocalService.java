package com.my.tea.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.tea.dto.CafeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KakaoLocalService {

    @Value("${kakao.rest-api-key}")
    private String restKey;

    private final String API_URL = "https://dapi.kakao.com/v2/local/search/keyword.json";

    public List<CafeDto> searchCafeByKeywordAndLocation(String keyword, double latitude, double longitude, int radius) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        String url = UriComponentsBuilder.fromHttpUrl(API_URL)
                .queryParam("query", keyword)
                .queryParam("category_group_code", "CE7") // 카페
                .queryParam("x", longitude)
                .queryParam("y", latitude)
                .queryParam("radius", radius)
                .queryParam("size", 10)
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + restKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode documents = root.path("documents");

        List<CafeDto> result = new ArrayList<>();
        for (JsonNode doc : documents) {
            double lat = Double.parseDouble(doc.path("y").asText());
            double lon = Double.parseDouble(doc.path("x").asText());

            result.add(new CafeDto(
                    doc.path("place_name").asText(),
                    doc.path("address_name").asText(),
                    lat,
                    lon
            ));
        }

        return result;
    }
}
