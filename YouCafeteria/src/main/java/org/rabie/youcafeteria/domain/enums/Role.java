package org.rabie.youcafeteria.domain.enums;

import java.util.Set;

public enum Role {
    ADMIN(Set.of(
            "CAN_MANAGE_USERS",
            "CAN_MANAGE_STOCKS",
            "CAN_MANAGE_MENU",
            "CAN_MANAGE_NOTIFICATIONS",
            "CAN_VIEW_EVALUATIONS",
            "CAN_ANALYZE_FEEDBACK")),

    CHEF(Set.of(
            "CAN_MANAGE_STOCKS",
            "CAN_MANAGE_MENU",
            "CAN_SET_DAILY_SPECIALS",
            "CAN_SEND_NOTIFICATIONS",
            "CAN_VIEW_EVALUATIONS")),

    STUDENT(Set.of(
            "CAN_RESERVE_MEALS",
            "CAN_MODIFY_RESERVATIONS",
            "CAN_EVALUATE_MEALS",
            "CAN_VIEW_ORDER_HISTORY",
            "CAN_CHOOSE_PICKUP_OPTION"));
    private final Set<String> permissions;

    Role(Set<String> permissions) {
        this.permissions = permissions;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

}
