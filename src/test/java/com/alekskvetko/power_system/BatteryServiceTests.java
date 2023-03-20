package com.alekskvetko.power_system;

import com.alekskvetko.power_system.domain.dto.Battery;
import com.alekskvetko.power_system.repository.BatteryRepository;
import com.alekskvetko.power_system.service.BatteryService;
import com.alekskvetko.power_system.service.BatteryServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = BatteryServiceImpl.class)
class BatteryServiceTests extends BatteryServiceBase {

  @Autowired
  private BatteryService batteryService;
  @MockBean
  private BatteryRepository batteryRepository;

  @Test
  void batteryServiceSaveTest() {
    batteryService.save(buildMockBatteryList());
    Mockito.verify(batteryRepository).saveAll(buildMockBatteryEntityList());
  }

  @Test
  void batteryServiceQueryRangeTest() {
    Mockito.when(batteryRepository.findBatteryEntitiesByPostCodeBetweenOrderByName(POST_CODE_START, POST_CODE_END)).thenReturn(buildMockBatteryEntityList());
    List<Battery> batteries = batteryService.query(POST_CODE_START, POST_CODE_END);
    assertNotNull(batteries);
    assertEquals(2, batteries.size());
    Battery battery = batteries.get(0);
    assertEquals("Battery1",battery.getName());
    assertEquals("B-123",battery.getPostCode());
    assertEquals(2400,battery.getCapacity());
    battery = batteries.get(1);
    assertEquals("Battery2",battery.getName());
    assertEquals("B-123",battery.getPostCode());
    assertEquals(5600,battery.getCapacity());
  }
}
