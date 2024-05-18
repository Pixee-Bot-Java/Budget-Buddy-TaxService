package com.skillstorm.taxservice.constants;

import lombok.Getter;

@Getter
public enum State {

    ALABAMA(1, "AL"), ALASKA(2, "AK"), ARIZONA(3, "AZ"), ARKANSAS(4, "AR"), CALIFORNIA(5, "CA"),
    COLORADO(6, "CO"), CONNECTICUT(7, "CT"), DELAWARE(8, "DE"), FLORIDA(9, "FL"), GEORGIA(10, "GA"),
    HAWAII(11, "HI"), IDAHO(12, "ID"), ILLINOIS(13, "IL"), INDIANA(14, "IN"), IOWA(15, "IA"),
    KANSAS(16, "KS"), KENTUCKY(17, "KY"), LOUISIANA(18, "LA"), MAINE(19, "ME"), MARYLAND(20, "MD"),
    MASSACHUSETTS(21, "MA"), MICHIGAN(22, "MI"), MINNESOTA(23, "MN"), MISSISSIPPI(24, "MS"),
    MISSOURI(25, "MO"), MONTANA(26, "MT"), NEBRASKA(27, "NE"), NEVADA(28, "NV"), NEW_HAMPSHIRE(29, "NH"),
    NEW_JERSEY(30, "NJ"), NEW_MEXICO(31, "NM"), NEW_YORK(32, "NY"), NORTH_CAROLINA(33, "NC"),
    NORTH_DAKOTA(34, "ND"), OHIO(35, "OH"), OKLAHOMA(36, "OK"), OREGON(37, "OR"), PENNSYLVANIA(38, "PA"),
    RHODE_ISLAND(39, "RI"), SOUTH_CAROLINA(40, "SC"), SOUTH_DAKOTA(41, "SD"), TENNESSEE(42, "TN"),
    TEXAS(43, "TX"), UTAH(44, "UT"), VERMONT(45, "VT"), VIRGINIA(46, "VA"), WASHINGTON(47, "WA"),
    WEST_VIRGINIA(48, "WV"), WISCONSIN(49, "WI"), WYOMING(50, "WY");

    // Convert String to State. Used to translate JSON from request to Java object:
    public static State fromCode(String stateCode) {
        for (State state : State.values()) {
            if (state.getCode().equalsIgnoreCase(stateCode)) {
                return state;
            }
        }
        return null;
    }


    // Convert int to State. Used to translate integer from database to Java object:
    public static State fromValue(int stateValue) {
        for (State state : State.values()) {
            if (state.getValue() == stateValue) {
                return state;
            }
        }
        return null; // Return null if no matching state is found
    }


    private final int value;
    private final String code;

    State(int value, String code) {
        this.value = value;
        this.code = code;
    }
}
