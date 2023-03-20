package com.alekskvetko.power_system;

import com.alekskvetko.power_system.controller.BatteryController;
import com.alekskvetko.power_system.domain.dto.Battery;
import com.alekskvetko.power_system.service.BatteryService;
import com.alekskvetko.power_system.service.StatisticsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BatteryController.class)
class BatteryControllerTests extends BatteryServiceBase {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private BatteryService batteryService;
  @MockBean
  private StatisticsService statisticsService;

  @Test
  void batteryControllerSaveTest() throws Exception {
    this.mockMvc
        .perform(post("/battery")
            .content(objectMapper.writeValueAsString(buildMockBatteryList()))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void batteryControllerQueryRangeTest() throws Exception {
    List<Battery> mockBatteryList = buildMockBatteryList();
    Mockito.when(batteryService.query(POST_CODE_START, POST_CODE_END)).thenReturn(mockBatteryList);
    Mockito.when(statisticsService.calculate(mockBatteryList)).thenReturn(buildMockBatteryStatistics());
    this.mockMvc
        .perform(get("/battery/query/postCodeStart/{postCodeStart}/postCodeEnd/{postCodeEnd}", POST_CODE_START, POST_CODE_END))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.names[0]").value("Battery1"))
        .andExpect(jsonPath("$.names[1]").value("Battery2"))
        .andExpect(jsonPath("$.totalCapacity").value(8000d))
        .andExpect(jsonPath("$.averageCapacity").value(4000d));
  }

  @Test
  void batteryControllerQueryRangeEmptyTest() throws Exception {
    Mockito.when(batteryService.query(POST_CODE_START, POST_CODE_END)).thenReturn(Collections.emptyList());
    Mockito.when(statisticsService.calculate(Collections.emptyList())).thenReturn(buildEmptyMockBatteryStatistics());
    this.mockMvc
        .perform(get("/battery/query/postCodeStart/{postCodeStart}/postCodeEnd/{postCodeEnd}", POST_CODE_START, POST_CODE_END))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.names").isEmpty())
        .andExpect(jsonPath("$.totalCapacity").value(0d))
        .andExpect(jsonPath("$.averageCapacity").value(0d));
  }

}
