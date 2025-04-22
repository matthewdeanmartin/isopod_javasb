package com.example.isopod;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@SessionAttributes("gameState") // This might not work in Lambda
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

    // Session based
//    @PostMapping("/action")
//    public String handleAction(@RequestParam String command, @ModelAttribute GameState gameState, Model model) {
//        String response = gameState.handleCommand(command);
//        model.addAttribute("description", response);
//        return "game";
//    }

    @PostMapping("/action")
    public String handleAction(
            @RequestParam String command,
            @RequestParam(required = false) String state, // JSON string
            Model model) {

        GameState gameState = (state != null && !state.isBlank())
                ? GameState.fromJson(state)
                : new GameState();
        String result = gameState.handleCommand(command);
        model.addAttribute("description", result);
        model.addAttribute("gameStateJson", gameState.toJson()); // Send it back for next turn
        return "game";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "✅ Lambda is working!";
    }
}
