package com.alekskvetko.power_system.service;

import com.alekskvetko.power_system.domain.dto.Battery;
import com.alekskvetko.power_system.domain.dto.BatteryStatistics;

import java.util.List;

public interface StatisticsService {
  BatteryStatistics calculate(List<Battery> batteries);
}
