package com.skillstorm.taxservice.constants;

import lombok.Getter;

@Getter
public enum FilingStatus {

    SINGLE(1), MARRIED_FILING_JOINTLY(2), MARRIED_FILING_SEPARATELY(3), HEAD_OF_HOUSEHOLD(4), WIDOW(5);

    // Convert String to FilingStatus. Used to translate JSON from request to Java object:
    public static FilingStatus fromString(String status) {
        return switch (status) {
            case "SINGLE" -> SINGLE;
            case "MARRIED_FILING_JOINTLY" -> MARRIED_FILING_JOINTLY;
            case "MARRIED_FILING_SEPARATELY" -> MARRIED_FILING_SEPARATELY;
            case "HEAD_OF_HOUSEHOLD" -> HEAD_OF_HOUSEHOLD;
            case "WIDOW" -> WIDOW;
            default -> null;
        };
    }

    // Convert int to FilingStatus. Used to translate integer from database to Java object:
    public static FilingStatus fromValue(int status) {
        return switch (status) {
            case 2 -> MARRIED_FILING_JOINTLY;
            case 3 -> MARRIED_FILING_SEPARATELY;
            case 4 -> HEAD_OF_HOUSEHOLD;
            case 5 -> WIDOW;
            default -> SINGLE;
        };
    }

    private final int value;

    FilingStatus(int value) {
        this.value = value;
    }
}
