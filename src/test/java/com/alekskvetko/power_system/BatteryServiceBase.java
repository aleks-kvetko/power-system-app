package com.alekskvetko.power_system;

import com.alekskvetko.power_system.domain.dto.Battery;
import com.alekskvetko.power_system.domain.dto.BatteryStatistics;
import com.alekskvetko.power_system.domain.entity.BatteryEntity;

import java.util.Arrays;
import java.util.List;

public class BatteryServiceBase {

  protected static final String POST_CODE_START = "B-120";
  protected static final String POST_CODE_END = "B-125";

  protected List<Battery> buildMockBatteryList() {
    return Arrays.asList(
        Battery.builder()
            .name("Battery1")
            .postCode("B-123")
            .capacity(2400d)
            .build(),
        Battery.builder()
            .name("Battery2")
            .postCode("B-123")
            .capacity(5600d)
            .build()
    );
  }

  protected List<BatteryEntity> buildMockBatteryEntityList() {
    return Arrays.asList(
        BatteryEntity.builder()
            .name("Battery1")
            .postCode("B-123")
            .capacity(2400d)
            .build(),
        BatteryEntity.builder()
            .name("Battery2")
            .postCode("B-123")
            .capacity(5600d)
            .build()
    );
  }

  protected BatteryStatistics buildMockBatteryStatistics() {
    return BatteryStatistics.builder()
        .averageCapacity(4000d)
        .totalCapacity(8000d)
        .build();
  }

  protected BatteryStatistics buildEmptyMockBatteryStatistics() {
    return BatteryStatistics.builder()
        .averageCapacity(0d)
        .totalCapacity(0d)
        .build();
  }
}
