package com.muliyul.pizzarch.notification.api

import com.muliyul.pizzarch.notification.model.*

interface NotificationService {
	fun sendNotification(notification: Notification)
}
