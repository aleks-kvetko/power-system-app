package com.alekskvetko.power_system.service;

import com.alekskvetko.power_system.domain.dto.Battery;

import java.util.List;

public interface BatteryService {
  void save(List<Battery> batteries);

  List<Battery> query(String postCodeStart, String postCodeEnd);
}
