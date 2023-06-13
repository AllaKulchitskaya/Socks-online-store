package com.example.termpaper_3.model;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.lang.Nullable;

public enum Size {
    SIZE_36(36),
    SIZE_36_5(36.5F),
    SIZE_37(37),
    SIZE_37_5(37.5F),
    SIZE_38(38),
    SIZE_38_5(38.5F),
    SIZE_39(39),
    SIZE_39_5(39.5F),
    SIZE_40(40),
    SIZE_40_5(40.5F),
    SIZE_41(41),
    SIZE_41_5(41.5F),
    SIZE_42(42),
    SIZE_42_5(42.5F),
    SIZE_43(43),
    SIZE_43_5(43.5F),
    SIZE_44(44),
    SIZE_44_5(44.5F),
    SIZE_45(45);

    private final float size;

    Size(float size) {
        this.size = size;
    }

    @JsonValue
    public float getSize() {
        return size;
    }

    @Nullable
    public static Size parse (float size) {
        for (Size s : values()) {
            if (Float.compare(s.size, size) == 0) {
                return s;
            }
        }
        return null;
    }
}
