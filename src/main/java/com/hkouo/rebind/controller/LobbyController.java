package com.hkouo.rebind.controller;

import com.hkouo.rebind.model.CustomUserDetails;
import com.hkouo.rebind.model.Scenario;
import com.hkouo.rebind.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LobbyController {

    @Autowired
    private ScenarioService scenarioService;

    @GetMapping("/")
    public String lobbyPage(@RequestParam(defaultValue = "1") int page,
                            @AuthenticationPrincipal CustomUserDetails userDetails,
                            Model model) {
        Long userIdx = userDetails.getIdx();
        int pageSize = 10;

        List<Scenario> scenarios = scenarioService.getScenariosForUser(userIdx, page, pageSize);
        model.addAttribute("scenarios", scenarios);
        model.addAttribute("page", page);

        return "lobby";
    }
}
