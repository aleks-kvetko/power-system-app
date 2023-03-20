package com.alekskvetko.power_system;

import com.alekskvetko.power_system.domain.dto.BatteryStatistics;
import com.alekskvetko.power_system.service.StatisticsService;
import com.alekskvetko.power_system.service.StatisticsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = StatisticsServiceImpl.class)
class StatisticsServiceTests extends BatteryServiceBase {


  @Autowired
  private StatisticsService statisticsService;

  @Test
  void statisticsServiceTest() {
    BatteryStatistics statistics = statisticsService.calculate(buildMockBatteryList());
    assertEquals(4000d, statistics.getAverageCapacity());
    assertEquals(8000d, statistics.getTotalCapacity());
  }

  @Test
  void statisticsServiceEmptyListTest() {
    BatteryStatistics statistics = statisticsService.calculate(Collections.emptyList());
    assertEquals(0d, statistics.getAverageCapacity());
    assertEquals(0d, statistics.getTotalCapacity());
  }

}
