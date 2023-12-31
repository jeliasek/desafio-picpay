package com.desafiopicpay.service;

import com.desafiopicpay.domain.user.User;
import com.desafiopicpay.dto.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception{
        String email = user.getEmail();
        NotificationDto notification = new NotificationDto(email, message);
        ResponseEntity<String> notificationResponse = restTemplate.postForEntity
                ("http://o4d9z.mocklab.io/notify", notification, String.class);

        if (notificationResponse.getStatusCode() != HttpStatus.OK) {
            System.out.println("~Notification service is offline.~");
            throw new Exception("Notification service is offline.");
        }

    }
}
