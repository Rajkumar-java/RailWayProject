package com.railway.railway_dashboard_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.railway.railway_dashboard_service.entity.KPI;

@Repository
public interface KPIRepository extends JpaRepository<KPI, Long>
{
     KPI findTopByOrderByIdDesc();
}
