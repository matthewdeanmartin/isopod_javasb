package com.example.isopod;

import java.util.*;

public class GameState {
    private final Map<String, Location> map = new HashMap<>();
    private final Set<String> inventory = new HashSet<>();
    private String currentLocation = "cave";

    public GameState() {
        buildMap();
    }

    private void buildMap() {
        Location cave = new Location("Cave 🕳️", "A dark damp place.");
        Location beach = new Location("Beach 🏖️", "Sandy and bright.");
        Location log = new Location("Log 🌲", "A fallen log, looks cozy.");
        Location tidePool = new Location("Tide Pool 🪸", "Salty water and small critters.");

        cave.setNeighbor("east", "beach");
        beach.setNeighbor("west", "cave");
        beach.setNeighbor("north", "log");
        log.setNeighbor("south", "beach");
        cave.setNeighbor("south", "tidePool");
        tidePool.setNeighbor("north", "cave");

        map.put("cave", cave);
        map.put("beach", beach);
        map.put("log", log);
        map.put("tidePool", tidePool);

        beach.setItem("cookie crumb 🍪");
        log.setItem("another isopod friend 🐛");
        tidePool.setItem("hiding place 🪨");
    }

    public String describeCurrentLocation() {
        Location loc = map.get(currentLocation);
        return loc.getDescriptionWithExits();
    }

    public String handleCommand(String command) {
        switch (command.toLowerCase()) {
            case "help":
                return "Available commands: move <direction>, look, inventory, help";
            case "look":
                return map.get(currentLocation).getFullDescription();
            case "inventory":
                return "Inventory: " + (inventory.isEmpty() ? "Nothing yet!" : String.join(", ", inventory));
            default:
                if (command.startsWith("move ")) {
                    return move(command.substring(5).trim());
                }
                return "Unknown command.";
        }
    }

    private String move(String direction) {
        Location loc = map.get(currentLocation);
        String next = loc.getNeighbor(direction);
        if (next == null) return "You can't go that way!";
        currentLocation = next;

        // Pick up item if present
        String item = map.get(currentLocation).takeItem();
        if (item != null) {
            inventory.add(item);
            if (inventory.containsAll(List.of("cookie crumb 🍪", "another isopod friend 🐛", "hiding place 🪨"))) {
                return map.get(currentLocation).getDescriptionWithExits() + "\n🎉 You found everything and won!";
            }
            return map.get(currentLocation).getDescriptionWithExits() + "\nYou found: " + item;
        }
        return map.get(currentLocation).getDescriptionWithExits();
    }
}
