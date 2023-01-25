package com.example.termpaper_3.model;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.lang.Nullable;

public enum Color {
    BLACK("black"),
    GRAY("gray"),
    WHITE("white"),
    RED("red"),
    BLUE("blue"),
    GREEN("green"),
    YELLOW("yellow");

    private final String string;

    Color(String string) {
        this.string = string;
    }

    @JsonValue
    public String getString() {
        return string;
    }

    @Nullable
    public static Color parse (String color) {
        for (Color c : values()) {
            if (c.name().equals(color)) {
                return c;
            }
        }
        return null;
    }
}
