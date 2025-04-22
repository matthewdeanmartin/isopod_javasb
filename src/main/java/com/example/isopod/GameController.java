package com.example.isopod;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
// @SessionAttributes("gameState")
public class GameController {

    @ModelAttribute("gameState")
    public GameState gameState() {
        return new GameState(); // starts fresh each session
    }

    @GetMapping("/")
    public String showGame(@ModelAttribute GameState gameState, Model model) {
        model.addAttribute("description", gameState.describeCurrentLocation());
        return "game";
    }

    @PostMapping("/action")
    public String handleAction(@RequestParam String command, @ModelAttribute GameState gameState, Model model) {
        String response = gameState.handleCommand(command);
        model.addAttribute("description", response);
        return "game";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "✅ Lambda is working!";
    }
}
