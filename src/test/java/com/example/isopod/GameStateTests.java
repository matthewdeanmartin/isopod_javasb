package com.example.isopod;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    private GameState gameState;

    @BeforeEach
    void setUp() {
        gameState = new GameState();
    }

    @Test
    void testInitialLocation() {
        String description = gameState.describeCurrentLocation();
        assertTrue(description.contains("Cave") || description.contains("🕳️"), "Initial location should be cave");
    }

    @Test
    void testMoveValidDirection() {
        String response = gameState.handleCommand("move east");
        assertTrue(response.contains("Beach") || response.contains("🏖️"), "Should move to beach");
    }

    @Test
    void testMoveInvalidDirection() {
        String response = gameState.handleCommand("move north");
        assertTrue(response.contains("can't go that way"), "Should block invalid direction");
    }

    @Test
    void testPickUpItem() {
        String response = gameState.handleCommand("move east"); // Beach has 🍪
        assertTrue(response.contains("cookie crumb"), "Should find cookie crumb");

        // gameState.handleCommand("look"); // picks it up
        String inventory = gameState.handleCommand("inventory");
        assertTrue(inventory.contains("🍪"), "Inventory should include cookie crumb");
    }

    @Test
    void testWinCondition() {
        gameState.handleCommand("move south"); // tide pool - 🪨
        gameState.handleCommand("look");

        gameState.handleCommand("move north"); // back to cave
        gameState.handleCommand("move east"); // beach - 🍪
        gameState.handleCommand("look");

        String winResponse = gameState.handleCommand("move north"); // log - 🐛
        gameState.handleCommand("look");

        assertTrue(winResponse.contains("won"), "Should trigger win condition");
    }
}
