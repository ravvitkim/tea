package com.my.tea.controller;

import com.my.tea.dto.CafeDto;
import com.my.tea.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cafes")
@RequiredArgsConstructor
public class CafeController {

    private final CafeService cafeService;

    @GetMapping("/recommend")
    public List<CafeDto> recommendCafes(
            @RequestParam String keyword,
            @RequestParam double latitude,
            @RequestParam double longitude) throws Exception {

        return cafeService.getRecommendedCafes(keyword, latitude, longitude);
    }
}
