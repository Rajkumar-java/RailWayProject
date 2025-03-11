package com.railway.railway_dashboard_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.railway.railway_dashboard_service.entity.Notifications;

@Repository
public interface NotificationRepository extends JpaRepository<Notifications, Long>
{

}
