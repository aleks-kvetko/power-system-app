package com.alekskvetko.power_system.domain.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class BatterySearchResult {
  List<String> names;
  Double totalCapacity;
  Double averageCapacity;
}
