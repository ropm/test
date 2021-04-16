package com.vaadin.starter.bakery.backend.data;

import java.time.LocalDateTime;

public class AuditLogItem {
    private LocalDateTime localDateTime;
    private String userName;
    private String message;

    public AuditLogItem(LocalDateTime localDateTime, String userName, String message) {
        this.localDateTime = localDateTime;
        this.userName = userName;
        this.message = message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }


    public String getUserName() {
        return userName;
    }


    public String getMessage() {
        return message;
    }

}
