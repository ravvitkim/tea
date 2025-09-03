package com.my.tea.controller;

import com.my.tea.service.StateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/state")
public class StateController {

    private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping("/select")
    public String selectState(Model model) {
        model.addAttribute("stateList", stateService.findAllState());
        return "stateSelect"; // 상태 선택 페이지
    }

    @GetMapping("/recommend/{stateId}")
    public String recommendTea(@PathVariable Long stateId, Model model) {
        model.addAttribute("teas", stateService.findTeasByState(stateId));
        return "teaList"; // 추천된 차 목록 페이지
    }
}

