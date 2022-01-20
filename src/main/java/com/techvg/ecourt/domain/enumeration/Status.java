package com.techvg.ecourt.domain.enumeration;

/**
 * The Status enumeration.
 */
public enum Status {
    OPEN("Open"),
    HEARING("Hearing"),
    NEXT_HEARING("NextHearing"),
    AWAITING("Awaiting"),
    CLOSED("Closed"),
    CONCLUDED("Concluded"),
    SUSPENDED("Suspended");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
