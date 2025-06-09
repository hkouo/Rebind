package com.hkouo.rebind.controller;

import com.hkouo.rebind.model.*;
import com.hkouo.rebind.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
                                      @RequestParam("imageFile") MultipartFile imageFile,
                                      @AuthenticationPrincipal CustomUserDetails userDetails) {
        scenario.setImageFile(imageFile);
        Long scenarioIdx = scenarioService.createScenarioStep1(scenario, userDetails.getIdx());
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
        form.setScenarioIdx(scenarioIdx);
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
