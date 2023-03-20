package com.alekskvetko.power_system.domain.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BatteryStatistics {
  Double totalCapacity;
  Double averageCapacity;
}
