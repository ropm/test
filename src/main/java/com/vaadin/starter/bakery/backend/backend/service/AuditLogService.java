package com.vaadin.starter.bakery.backend.service;

import com.vaadin.starter.bakery.app.security.SecurityUtils;
import com.vaadin.starter.bakery.backend.data.AuditLogItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class AuditLogService {

    private static List<AuditLogItem> auditMessages = new ArrayList<>();

    public static void addAuditLogEntry(String message) {
        String userName = SecurityUtils.getUsername();
        String auditMessage = new Date().toString() + ": " + userName
                + ": " + message;
        synchronized (auditMessages) {
            auditMessages.add(new AuditLogItem(LocalDateTime.now(), userName, message));
        }
    }

    public static List<AuditLogItem> getAuditLogItems() {
        return Collections.unmodifiableList(auditMessages);
    }

}
