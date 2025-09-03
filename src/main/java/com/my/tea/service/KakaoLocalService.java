import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.tea.dto.CafeDto;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KakaoLocalService {

    @Value("${kakao.rest-api-key}")
    private String REST_KEY;
    private final String API_URL = "https://dapi.kakao.com/v2/local/search/keyword.json";

    public List<CafeDto> searchCafeByKeywordAndLocation(String keyword, String x, String y, int radius) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        String url = UriComponentsBuilder.fromHttpUrl(API_URL)
                .queryParam("query", keyword)
                .queryParam("category_group_code", "CE7")
                .queryParam("x", x)
                .queryParam("y", y)
                .queryParam("radius", radius)
                .queryParam("size", 10)
                .toUriString();

        HttpHeaders  headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + REST_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode documents = root.path("documents");

        List<CafeDto> result = new ArrayList<>();
        for (JsonNode doc : documents) {
            result.add(new CafeDto(
                    doc.path("place_name").asText(),
                    doc.path("address_name").asText(),
                    doc.path("phone").asText(),
                    doc.path("x").asText(),
                    doc.path("y").asText()
            ));
        }
        return result;
    }
}
