package com.example.isopod;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

public class Location {
    private String name;
    private String description;
    private Map<String, String> neighbors = new HashMap<>();
    private String item;


    // ✅ Required for Jackson
    public Location() {
    }

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // ✅ Add public getters/setters for all fields

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Map<String, String> neighbors) {
        this.neighbors = neighbors;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }


    public void setNeighbor(String direction, String location) {
        neighbors.put(direction, location);
    }


    public String takeItem() {
        String found = item;
        item = null;
        return found;
    }

    public String getNeighbor(String direction) {
        return neighbors.get(direction);
    }

    @JsonIgnore
    public String getDescriptionWithExits() {
        return name + ": " + description + "\nExits: " + String.join(", ", neighbors.keySet());
    }

    @JsonIgnore
    public String getFullDescription() {
        return getDescriptionWithExits() + (item != null ? "\nYou see: " + item : "");
    }
}
