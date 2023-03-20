package com.alekskvetko.power_system.repository;


import com.alekskvetko.power_system.domain.entity.BatteryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BatteryRepository extends CrudRepository<BatteryEntity, String> {

  /**
   * In this example we calculate statistics in memory
   * In production case it might be better to do such calculations in the database + add pagination
   */
  List<BatteryEntity> findBatteryEntitiesByPostCodeBetweenOrderByName(String postCodeStart, String postCodeEnd);
}
