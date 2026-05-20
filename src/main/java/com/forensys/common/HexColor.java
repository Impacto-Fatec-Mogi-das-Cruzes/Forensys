package com.forensys.common;

import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

public final class HexColor {

    private static final Pattern HEX_PATTERN =
            Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{8})$");

    private final String value;

    private HexColor(String value) {
        this.value = normalize(value);
    }

    public static HexColor of(String value) {
        validate(value);
        return new HexColor(value);
    }

    public String value() {
        return value;
    }

    public int red() {
        return Integer.parseInt(value.substring(1, 3), 16);
    }

    public int green() {
        return Integer.parseInt(value.substring(3, 5), 16);
    }

    public int blue() {
        return Integer.parseInt(value.substring(5, 7), 16);
    }

    public int alpha() {
        if (value.length() == 9) {
            return Integer.parseInt(value.substring(7, 9), 16);
        }
        return 255;
    }

    public boolean hasAlpha() {
        return value.length() == 9;
    }

    private static void validate(String value) {
        Objects.requireNonNull(value, "Hex color cannot be null");

        if (!HEX_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException(
                    "Invalid hex color: " + value +
                    ". Expected format: #RRGGBB or #RRGGBBAA"
            );
        }
    }

    private static String normalize(String value) {
        return value.toUpperCase(Locale.ROOT);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HexColor that)) return false;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
