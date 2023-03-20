package com.alekskvetko.power_system.controller;

import com.alekskvetko.power_system.domain.dto.Battery;
import com.alekskvetko.power_system.domain.dto.BatterySearchResult;
import com.alekskvetko.power_system.domain.dto.BatteryStatistics;
import com.alekskvetko.power_system.service.BatteryService;
import com.alekskvetko.power_system.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/battery")
@RequiredArgsConstructor
public class BatteryController {

  private final BatteryService batteryService;
  private final StatisticsService statisticsService;

  @PostMapping
  public void save(@RequestBody List<Battery> batteries) {
    batteryService.save(batteries);
  }

  @GetMapping("query/postCodeStart/{postCodeStart}/postCodeEnd/{postCodeEnd}")
  public BatterySearchResult query(@PathVariable("postCodeStart") String postCodeStart, @PathVariable("postCodeEnd") String postCodeEnd) {
    List<Battery> batteries = batteryService.query(postCodeStart, postCodeEnd);
    BatteryStatistics batteryStatistics = statisticsService.calculate(batteries);
    List<String> batteryNames = batteries.stream()
        .map(Battery::getName)
        .collect(Collectors.toList());
    return BatterySearchResult.builder()
        .averageCapacity(batteryStatistics.getAverageCapacity())
        .totalCapacity(batteryStatistics.getTotalCapacity())
        .names(batteryNames)
        .build();
  }

}
