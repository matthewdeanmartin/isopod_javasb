package com.example.isopod;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    private Location cave;
    private Location beach;

    @BeforeEach
    void setUp() {
        cave = new Location("Cave 🕳️", "A dark damp place.");
        beach = new Location("Beach 🏖️", "Sandy and bright.");
    }

    @Test
    void testInitialDescription() {
        String desc = cave.getDescriptionWithExits();
        assertTrue(desc.contains("Cave") && desc.contains("Exits"), "Initial description should contain name and exits");
    }

    @Test
    void testNeighbors() {
        cave.setNeighbor("east", "beach");
        assertEquals("beach", cave.getNeighbor("east"), "Cave should have beach to the east");
        assertNull(cave.getNeighbor("north"), "No neighbor to the north");
    }

    @Test
    void testItemPlacementAndPickup() {
        cave.setItem("cookie crumb 🍪");
        String fullDesc = cave.getFullDescription();
        assertTrue(fullDesc.contains("cookie crumb"), "Full description should show item");

        String found = cave.takeItem();
        assertEquals("cookie crumb 🍪", found, "Should retrieve the item");
        assertNull(cave.takeItem(), "Second call should return null");
    }

    @Test
    void testNoItemByDefault() {
        assertNull(cave.takeItem(), "Should be null when no item is set");
    }

    @Test
    void testExitsDisplay() {
        cave.setNeighbor("north", "beach");
        String exits = cave.getDescriptionWithExits();
        assertTrue(exits.contains("north"), "Exits should list directions");
    }
}

