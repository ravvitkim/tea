package com.my.tea.controller;

import com.my.tea.dto.MoodCheckDto;
import com.my.tea.dto.MoodDto;
import com.my.tea.service.MoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mood")
public class MoodController {

    private final MoodService moodService;

    public MoodController(MoodService moodService) {
        this.moodService = moodService;
    }

    // 체크리스트 보여주기
    @GetMapping("/select")
    public String selectMood(Model model) {
        List<MoodCheckDto> checkList = moodService.getMoodCheckList();
        model.addAttribute("checkList", checkList);
        return "moodCheckList"; // Thymeleaf 뷰
    }

    // 체크 후 결과
    @PostMapping("/select")
    public String submitMood(@RequestParam List<Long> checkedIds, Model model) {
        MoodDto resultMood = moodService.calculateMood(checkedIds);
        model.addAttribute("resultMood", resultMood);
        return "moodResult"; // 결과 뷰
    }
}
