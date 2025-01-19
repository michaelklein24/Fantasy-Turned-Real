package com.kleintwins.ftr.core;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "Sort order for SQL query", enumAsRef = true)
public enum SortOrder {
    ASC("asc"),
    DESC("desc");

    private final String value;

    SortOrder(String value) {
        this.value = value;
    }

    public static SortOrder fromString(String value) {
        for (SortOrder order : SortOrder.values()) {
            if (order.value.equalsIgnoreCase(value)) {
                return order;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + value);
    }
}
