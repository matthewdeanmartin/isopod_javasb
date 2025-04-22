package com.example.isopod;

import java.util.HashMap;
import java.util.Map;

public class Location {
    private final String name;
    private final String description;
    private final Map<String, String> neighbors = new HashMap<>();
    private String item;

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setNeighbor(String direction, String location) {
        neighbors.put(direction, location);
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String takeItem() {
        String found = item;
        item = null;
        return found;
    }

    public String getNeighbor(String direction) {
        return neighbors.get(direction);
    }

    public String getDescriptionWithExits() {
        return name + ": " + description + "\nExits: " + String.join(", ", neighbors.keySet());
    }

    public String getFullDescription() {
        return getDescriptionWithExits() + (item != null ? "\nYou see: " + item : "");
    }
}
