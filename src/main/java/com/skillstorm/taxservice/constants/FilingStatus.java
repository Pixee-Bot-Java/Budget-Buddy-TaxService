package com.skillstorm.taxservice.constants;

import lombok.Getter;

@Getter
public enum FilingStatus {

    SINGLE(0), MARRIED(1), HEAD_OF_HOUSEHOLD(2), WIDOW(3);

    // Convert String to FilingStatus. Used to translate JSON from request to Java object:
    public static FilingStatus fromString(String status) {
        return switch (status) {
            case "SINGLE" -> SINGLE;
            case "MARRIED" -> MARRIED;
            case "HEAD_OF_HOUSEHOLD" -> HEAD_OF_HOUSEHOLD;
            case "WIDOW" -> WIDOW;
            default -> null;
        };
    }

    // Convert int to FilingStatus. Used to translate integer from database to Java object:
    public static FilingStatus fromValue(int status) {
        return switch (status) {
            case 1 -> MARRIED;
            case 2 -> HEAD_OF_HOUSEHOLD;
            case 3 -> WIDOW;
            default -> SINGLE;
        };
    }

    private final int value;

    FilingStatus(int value) {
        this.value = value;
    }
}
