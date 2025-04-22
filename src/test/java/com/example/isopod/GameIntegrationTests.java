package com.example.isopod;

import com.example.isopod.GameState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.mock.web.MockHttpSession;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GameIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void homepageRendersWithStartingLocation() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Exits")));
    }

    @Test
    void moveToAnotherLocation() throws Exception {
        MockHttpSession session = new MockHttpSession();

        // Start session and render initial game screen
        mockMvc.perform(get("/").session(session))
                .andExpect(status().isOk());

        // Move east (to beach)
        mockMvc.perform(post("/action")
                        .param("command", "move east")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Beach")));
    }

    @Test
    void helpCommandOutputsHelpText() throws Exception {
        mockMvc.perform(post("/action")
                        .param("command", "help")
                        .session(new MockHttpSession()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Available commands")));
    }

    @Test
    void inventoryShowsAfterPickup() throws Exception {
        MockHttpSession session = new MockHttpSession();

        // Move to beach, where 🍪 is
        mockMvc.perform(post("/action")
                        .param("command", "move east")
                        .session(session))
                .andExpect(status().isOk());

        // Look around (should pick up item)
        mockMvc.perform(post("/action")
                        .param("command", "look")
                        .session(session))
                .andExpect(status().isOk());

        // Check inventory
        mockMvc.perform(post("/action")
                        .param("command", "inventory")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("🍪")));
    }
}

