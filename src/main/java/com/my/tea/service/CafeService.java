package com.my.tea.service;

import com.my.tea.dto.CafeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CafeService {

    private final KakaoLocalService kakaoLocalService;

    /**
     * 키워드 + 좌표 기반으로 추천 카페 (반경 10km 내, 최대 3개)
     */
    public List<CafeDto> getRecommendedCafes(String keyword, double latitude, double longitude) throws Exception {
        int radius = 10000; // 10km
        List<CafeDto> cafes = kakaoLocalService.searchCafeByKeywordAndLocation(keyword, latitude, longitude, radius);

        // 최대 3개만 추천
        return cafes.size() > 3 ? cafes.subList(0, 3) : cafes;
    }
}
