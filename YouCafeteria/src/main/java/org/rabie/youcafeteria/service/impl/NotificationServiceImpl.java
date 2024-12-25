package org.rabie.youcafeteria.service.impl;

import org.rabie.youcafeteria.repository.NotificationRepository;
import org.rabie.youcafeteria.service.NotificationService;
import org.springframework.stereotype.Component;

@Component
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
}
