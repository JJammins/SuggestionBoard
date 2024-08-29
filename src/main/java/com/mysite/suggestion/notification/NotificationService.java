package com.mysite.suggestion.notification;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NotificationService {

	private final NotificationRepository notificationRepository;
	
	public List<Notification> getList(){
		return this.notificationRepository.findAll();
	}
}
