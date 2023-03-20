package com.alekskvetko.power_system.service;

import com.alekskvetko.power_system.domain.dto.Battery;
import com.alekskvetko.power_system.domain.dto.BatteryStatistics;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {


  /**
   * In this example we calculate statistics in memory
   * so we can take advantage of java stream functionalities
   * In production case it might be better to do such calculations in the database + add pagination
   */
  @Override
  public BatteryStatistics calculate(List<Battery> batteries) {
    DoubleSummaryStatistics doubleSummaryStatistics = batteries.stream()
        .mapToDouble(Battery::getCapacity)
        .summaryStatistics();
    return BatteryStatistics.builder()
        .averageCapacity(doubleSummaryStatistics.getAverage())
        .totalCapacity(doubleSummaryStatistics.getSum())
        .build();
  }
}
