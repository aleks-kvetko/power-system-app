package com.alekskvetko.power_system.service;

import com.alekskvetko.power_system.domain.dto.Battery;
import com.alekskvetko.power_system.domain.entity.BatteryEntity;
import com.alekskvetko.power_system.repository.BatteryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BatteryServiceImpl implements BatteryService {

  private final BatteryRepository batteryRepository;

  @Override
  public void save(List<Battery> batteries) {
    List<BatteryEntity> batteryEntities = batteries.stream()
        .map(this::map)
        .collect(Collectors.toList());
    batteryRepository.saveAll(batteryEntities);
  }

  @Override
  public List<Battery> query(String postCodeStart, String postCodeEnd) {
    return batteryRepository.findBatteryEntitiesByPostCodeBetweenOrderByName(postCodeStart, postCodeEnd)
        .stream()
        .map(this::map)
        .collect(Collectors.toList());
  }


  /**
   * Plain java mapping here
   * If he had many classes to map it would be easier to use mapping lib
   * (Mapstruct for example)
   */
  private BatteryEntity map(Battery battery) {
    return BatteryEntity.builder()
        .name(battery.getName())
        .postCode(battery.getPostCode())
        .capacity(battery.getCapacity())
        .build();
  }

  private Battery map(BatteryEntity battery) {
    return Battery.builder()
        .name(battery.getName())
        .postCode(battery.getPostCode())
        .capacity(battery.getCapacity())
        .build();
  }
}
