package com.hkouo.rebind.controller;

import com.hkouo.rebind.model.CustomUserDetails;
import com.hkouo.rebind.model.Scenario;
import com.hkouo.rebind.model.ScenarioChapterForm;
import com.hkouo.rebind.model.ScenarioCharacterForm;
import com.hkouo.rebind.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/scenario")
public class ScenarioController {

    @Autowired
    private ScenarioService scenarioService;

    @GetMapping("/create/step1")
    public String scenarioStep1Form(Model model) {
        model.addAttribute("scenario", new Scenario());
        return "scenario/create_step1";
    }

    @PostMapping("/create/step1")
    public String handleScenarioStep1(@ModelAttribute Scenario scenario,
                                      @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long creatorUserIdx = userDetails.getIdx();
        Long scenarioIdx = scenarioService.createScenarioStep1(scenario, creatorUserIdx);
        return "redirect:/scenario/create/step2?scenarioIdx=" + scenarioIdx;
    }
    @GetMapping("/create/step2")
    public String scenarioStep2Form(@RequestParam("scenarioIdx") Long scenarioIdx, Model model) {
        model.addAttribute("scenarioIdx", scenarioIdx);
        return "scenario/create_step2";
    }

    @PostMapping("/create/step2")
    public String handleScenarioStep2(@RequestParam("scenarioIdx") Long scenarioIdx,
                                      @ModelAttribute ScenarioCharacterForm form) {
        form.setScenarioIdx(scenarioIdx); // 시나리오 ID를 명확히 설정
        scenarioService.saveScenarioCharacters(form);
        return "redirect:/scenario/create/step3?scenarioIdx=" + scenarioIdx;
    }

    @GetMapping("/create/step3")
    public String scenarioStep3Form(@RequestParam("scenarioIdx") Long scenarioIdx, Model model) {
        model.addAttribute("scenarioIdx", scenarioIdx);
        model.addAttribute("form", new ScenarioChapterForm());
        return "scenario/create_step3";
    }

    @PostMapping("/create/step3")
    public String handleScenarioStep3(@ModelAttribute ScenarioChapterForm form) {
        scenarioService.saveScenarioChapters(form);
        return "redirect:/scenario/create/done?scenarioIdx=" + form.getScenarioIdx();
    }



}
